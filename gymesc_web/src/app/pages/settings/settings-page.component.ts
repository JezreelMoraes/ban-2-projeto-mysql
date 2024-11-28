import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { Router } from '@angular/router';
import { AuthService } from '../../shared/http/auth.service';
import { BreadcrumbItemComponent } from "../../components/breadcrumb-item/breadcrumb-item.component";
import { BreadcrumbComponent } from "../../components/breadcrumb/breadcrumb.component";
import { ContainerComponent } from "../../components/container/container.component";
import { FormComponent } from '../../components/form/form.component';
import { IllustrationComponent } from "../../components/illustration/illustration.component";
import { UserService } from '../../shared/services/user.service';
import { UserModel } from '../../shared/model/user-model';

@Component({
    selector: 'app-settings',
    standalone: true,
    imports: [
        BreadcrumbComponent,
        BreadcrumbItemComponent,
        FormComponent,
        ContainerComponent,
        IllustrationComponent,
        FormsModule,
        CommonModule
    ],
    templateUrl: './settings-page.component.html',
    styleUrl: './settings-page.component.scss'
})
export class SettingsPageComponent {

    userData: UserModel = new UserModel();

    userDataOriginal: UserModel = new UserModel();

    showSaveButton = false;

    constructor(private _userService: UserService, private authService: AuthService, private router: Router) {
    }

    ngOnInit(): void {
        this.loadUserData();
    }

    loadUserData() {
        this._userService.loadUserData().subscribe({
            next: (response: any) => {
                this.userData = response;
                this.userDataOriginal = { ...response };
            },
            error: (error: any) => {
                alert(`Erro: ${ error.message }`);
            }
        });
    }

    onChanges() {
        this.showSaveButton =
            this.userData.email !== this.userDataOriginal.email ||
            this.userData.name !== this.userDataOriginal.name ||
            this.userData.phoneNumber !== this.userDataOriginal.phoneNumber ||
            this.userData.federalIdentification !== this.userDataOriginal.federalIdentification ||
            this.userData.weight !== this.userDataOriginal.weight ||
            this.userData.height !== this.userDataOriginal.height ||
            this.userData.workoutLevelEnum !== this.userDataOriginal.workoutLevelEnum;
    }

    saveChanges() {
        this._userService.updateUser(this.userData).subscribe({
            next: () => {
                alert('Dados atualizados com sucesso');
                this.showSaveButton = false;
            },
            error: (err) => {
                alert('Erro ao atualizar os dados: ' + err.message);
            }
        });
    }

    deleteAccount() {
        if (!confirm('Tem certeza de que deseja apagar sua conta? Esta ação não pode ser desfeita.')) return;

        this._userService.deleteUser().subscribe({
            next: () => {
                alert('Conta apagada com sucesso');
                this.authService.removeAuthToken();
                this.router.navigateByUrl('/login');
            },
            error: (err) => {
                alert('Erro ao apagar a conta' + err.message);
            }
        });
    }

}
