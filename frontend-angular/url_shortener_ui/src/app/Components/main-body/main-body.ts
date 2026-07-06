import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { UrlService } from '../../Services/url-service';

@Component({
  selector: 'app-main-body',
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './main-body.html',
  styleUrl: './main-body.css',
})
export class MainBody {
  // Variables Declaration area
  // query = '';
  // text = '';
  shortUrl = '';
  originalUrl = '';
  isLoading = false;
  // Variables Declaration area

  constructor(private urlService: UrlService,
    private cdr: ChangeDetectorRef
  ) { }

  onSearch() {
    this.isLoading = true;
    if (!this.originalUrl.trim()) {
      return;
    }
    this.isLoading = true;
    console.log('Searching for:', this.originalUrl);
    this.urlService
      .shortenUrl(this.originalUrl)
      .subscribe(res => {
        this.shortUrl = res.shortenedUrl;
        this.cdr.detectChanges();
        // console.log(this.isLoading);
        // console.log(this.shortUrl);
        // console.log(res);
        this.isLoading = false;
      });
  }

  copyText() {

    if (!this.shortUrl) {
      return;
    }
    navigator.clipboard.writeText(this.shortUrl)
      .then(() => {
        alert('Copied to clipboard!');
      })
      .catch(() => {
        alert('Failed to copy');
      });
  }
}
