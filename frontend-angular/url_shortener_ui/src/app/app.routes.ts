import { Routes } from '@angular/router';
import { About } from './Components/about/about';
import { Contact } from './Components/contact/contact';
import { URLLists } from './Components/urllists/urllists';
import { Home } from './Components/home/home';
import { MainBody } from './Components/main-body/main-body';
import { Rateus } from './Components/rateus/rateus';

export const routes: Routes = [
    { path: '', component: Home },
    { path: 'mainpage', component: MainBody },
    { path: 'about', component: About },
    { path: 'contact', component: Contact },
    { path: 'geturls', component: URLLists },
    { path: 'rateus', component: Rateus },

    // { path: 'geturls/:code', component: URLLists } // route with parameter
];
