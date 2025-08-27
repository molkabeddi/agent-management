import { Component } from '@angular/core';
import { AgentComponent } from './agents/agent/agent.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [AgentComponent],
  template: `<div class="container py-4"><app-agent></app-agent></div>`
})
export class AppComponent {}
