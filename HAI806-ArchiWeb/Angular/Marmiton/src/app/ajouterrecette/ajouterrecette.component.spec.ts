import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjouterrecetteComponent } from './ajouterrecette.component';

describe('AjouterrecetteComponent', () => {
  let component: AjouterrecetteComponent;
  let fixture: ComponentFixture<AjouterrecetteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AjouterrecetteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AjouterrecetteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
