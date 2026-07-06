import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UrlService, Url } from '../../Services/url-service';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-urllists',
  imports: [CommonModule],
  templateUrl: './urllists.html',
  styleUrl: './urllists.css',
})
export class URLLists implements OnInit{
  
  // for pagination
  page = 0;
  size = 10;
  totalPages = 0;
  totalElements = 0;
  // for pagination

  urls: Url[] = [];
  isLoading = false;

  constructor(private urlService: UrlService,
    private changeDetectorRef: ChangeDetectorRef
  ){}
  
  ngOnInit(): void {
    console.log("inside ngOnInit of urllist method");
    this.fetchURLS();
    this.changeDetectorRef.detectChanges();
  }

  // fetchURLS() {
  //   this.isLoading = true;

  //   this.urlService.getAllUrls().subscribe({
  //     next: (data) => {
  //       console.log(data);
  //       this.isLoading = false;
  //       this.urls = data;
  //       this.changeDetectorRef.detectChanges();
  //     },
  //     error: (err) => {
  //       console.error(err);
  //       this.isLoading = false;
  //     }
  //   });
  // }

  fetchURLS() {
    // loading icon enabled
    this.isLoading = true;
    this.urlService.getAllUrls(this.page, this.size).subscribe(res => {
      // disable loading icon once data loaded
      this.isLoading = false;
      this.urls = res.content;
      this.totalPages = res.totalPages;
      this.totalElements = res.totalElements;
      this.changeDetectorRef.detectChanges();
    });
  }

  nextPage() {
    if (this.page < this.totalPages - 1) {
      this.page++;
      this.fetchURLS();
    }
  }

  previousPage() {
    if (this.page > 0) {
      this.page--;
      this.fetchURLS();
    }
  }

  delete(urlId: number) {
    if (!confirm('Are you sure you want to delete?')){
      return;
    }

    this.urlService.deleteUrl(urlId).subscribe({
      next: () => {
        // remove from UI without reload
        this.urls = this.urls.filter(u => u.urlId !== urlId);
        alert("Record deleted successfully");
        this.fetchURLS();
      },
      error: (err) => {
        console.error(err);
      }
    });
  }
}