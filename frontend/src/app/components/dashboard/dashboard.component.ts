import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  
  // Sample data for dashboard cards
  cards = [
    { title: 'Total Products', value: '0', icon: 'pi pi-shopping-cart', color: '#3498db' },
    { title: 'Total Users', value: '0', icon: 'pi pi-users', color: '#2ecc71' },
    { title: 'Recent Orders', value: '0', icon: 'pi pi-shopping-bag', color: '#e74c3c' },
    { title: 'Revenue', value: '$0', icon: 'pi pi-dollar', color: '#f39c12' }
  ];

  constructor() { }

  ngOnInit(): void {
    // In a real application, you would fetch this data from a service
  }
}