import { Component } from '@angular/core';
import { UserService, User } from './user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Marmiton';
  
  constructor(public userService : UserService) { }

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
