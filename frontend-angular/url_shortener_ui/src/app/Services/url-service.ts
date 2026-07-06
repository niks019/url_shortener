import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Url {
  urlId: number;
  longUrl: string;
  shortenedUrl: string;
  accessStats: number;
  createdAt: Date
}

@Injectable({
  providedIn: 'root',
})

export class UrlService {
  private api = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  // getAllUrls(): Observable<Url[]> {
  //   return this.http.get<Url[]>(`${this.api}/geturls`);
  // }

  getAllUrls(page: number, size: number) {
    return this.http.get<any>(
      `${this.api}/geturls?page=${page}&size=${size}`
    );
  }

  getUrl(code: string) {
    return this.http.get<any>(
      `${this.api}/geturls/${code}`
    );
  }

  shortenUrl(longUrl: string) {
    return this.http.post<any>(
      `${this.api}/shorten`,
      {longUrl}
    );
  }

  deleteUrl(urlId: number) {
    return this.http.delete(`${this.api}/geturls/${urlId}`);
  }

  submitMyRating(rating: number){
    console.log(rating);
    return this.http.post(`${this.api}/rateus`,
      rating
    )
  }

}