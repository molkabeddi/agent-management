import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatTooltipModule } from '@angular/material/tooltip';

import { AgentService, Agent } from './../agent.service';

@Component({
  selector: 'app-agent',
  standalone: true,
  imports: [
    CommonModule, DatePipe,
    MatCardModule, MatTableModule, MatPaginatorModule, MatSortModule,
    MatFormFieldModule, MatInputModule, MatIconModule, MatChipsModule,
    MatButtonModule, MatProgressSpinnerModule, MatTooltipModule
  ],
  templateUrl: './agent.component.html',
  styleUrls: ['./agent.component.scss']
})
export class AgentComponent implements OnInit {
  loading = false;
  error?: string;

  displayedColumns = [
    'userId', 'fullname', 'email', 'emailVerified', 'roles',
    'active', 'dateOfBirth', 'lastLogin'
  ];
  dataSource = new MatTableDataSource<Agent>([]);

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private agentService: AgentService) {}

  ngOnInit(): void {
    this.fetchAgents();
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;

    this.dataSource.filterPredicate = (data, filter) => {
      const s = (v?: string | null) => (v ?? '').toLowerCase();
      const target = `${s(data.userId)} ${s(data.fullname)} ${s(data.email)}`;
      return target.includes(filter.trim().toLowerCase());
    };
  }

  applyFilter(value: string) {
    this.dataSource.filter = value.trim().toLowerCase();
    if (this.dataSource.paginator) this.dataSource.paginator.firstPage();
  }

  fetchAgents(): void {
    this.loading = true;
    this.agentService.getAgents().subscribe({
      next: (data) => {
        this.dataSource.data = data ?? [];
        this.loading = false;
      },
      error: (err) => {
        console.error(err);
        this.error = 'Erreur de chargement';
        this.loading = false;
      }
    });
  }

  roleClass(r: string): string {
    switch (r) {
      case 'ROLE_ADMIN': return 'chip-admin';
      case 'ROLE_MODERATOR': return 'chip-moderator';
      case 'ROLE_USER': return 'chip-user';
      default: return 'chip-user';
    }
  }
  roleIcon(r: string): string {
    switch (r) {
      case 'ROLE_ADMIN': return 'star';
      case 'ROLE_MODERATOR': return 'security';
      case 'ROLE_USER': return 'person';
      default: return 'help';
    }
  }
}
