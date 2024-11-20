import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users: User[] | undefined;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe(response => {
      this.users = response;
    }, error => {
      console.error('Error fetching users:', error);
    });
  }

  deleteUser(userId: number): void {
    // Logique pour supprimer un utilisateur via le service
    this.userService.deleteUser(userId).subscribe(() => {
      this.users = this.users?.filter(user => user.id !== userId);
    }, error => {
      console.error('Error deleting user:', error);
    });
  }
}
