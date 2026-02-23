import { ApplicationConfig, provideBrowserGlobalErrorListeners, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { provideHttpClient } from '@angular/common/http'; //tool to talk to my Java backend

//create a var that can be imported to see how app should start
export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true}), //change detectioned triggered once (optmizes speed)
    provideRouter(routes), //tells angular how to handle routes
    provideHttpClient() //enables web request engine
  ]
};
