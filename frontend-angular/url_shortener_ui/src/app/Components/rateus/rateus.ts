import { ChangeDetectorRef, Component } from '@angular/core';
import { CommonModule, NgIf} from '@angular/common';
import { UrlService } from '../../Services/url-service';

@Component({
  selector: 'app-rateus',
  imports: [CommonModule, NgIf],
  templateUrl: './rateus.html',
  styleUrl: './rateus.css',
})
export class Rateus {

  constructor(
    private urlService: UrlService,
    private changeDetectorRef: ChangeDetectorRef
  ){}

  submitted: boolean = false;
  numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

  rating = 0;

  setRating(value: number) {
    this.rating = value;
    console.log(this.rating);
  }

  submitRating() {
    console.log("Submit clicked", this.rating);
    this.urlService.submitMyRating(this.rating).subscribe({
      next: () => {
        console.log("Success response received")
        this.submitted = true
        this.changeDetectorRef.detectChanges();
        console.log(this.submitted)
      },
    });
  }
}
