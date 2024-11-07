import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { filter, map } from 'rxjs/operators';

@Component({
  selector: 'app-folder',
  templateUrl: './folder.page.html',
  styleUrls: ['./folder.page.scss'],
})
export class FolderPage implements OnInit {
  pageTitle: string = 'Polis Healthy Hospital';

  constructor(private activatedRoute: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd),
      map(() => {
        let child = this.activatedRoute.firstChild;
        while (child && child.firstChild) {
          child = child.firstChild;
        }
        return child ? child.snapshot.data : {};
      })
    ).subscribe((data: any) => {
      this.pageTitle = data.title || 'Polis Healthy Hospital';
    });
  }
}
