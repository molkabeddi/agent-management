import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

export interface Agent {
  userId: string;
  fullname: string;
  email: string;
  emailVerified: boolean;
  roles: string[];
  active: boolean;
  dateOfBirth?: string | null;
  lastLogin?: string | null;
}

@Injectable({ providedIn: 'root' })
export class AgentService {
  private base = environment.apiBase;
  constructor(private http: HttpClient) {}

  getAgents(): Observable<Agent[]> {
    return this.http.get<Agent[]>(`${this.base}/agents`);
  }
}
