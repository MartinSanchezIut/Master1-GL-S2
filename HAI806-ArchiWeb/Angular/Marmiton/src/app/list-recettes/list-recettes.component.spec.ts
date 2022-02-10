import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListRecettesComponent } from './list-recettes.component';

describe('ListRecettesComponent', () => {
  let component: ListRecettesComponent;
  let fixture: ComponentFixture<ListRecettesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListRecettesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListRecettesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
