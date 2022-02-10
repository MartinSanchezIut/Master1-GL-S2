import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Marmiton';
  

  showHideMenu() {
    let menu = document.getElementById("hideAbleMenu");
    if (menu?.classList.contains("hide")) {
      menu.classList.remove("hide");
    }else {
      if (menu != null) {
        menu.classList.add("hide");
      }
    }
  }
}
