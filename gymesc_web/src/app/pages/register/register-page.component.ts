import { Component } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { Router, RouterLink } from "@angular/router";
import { ContainerComponent } from "../../components/container/container.component";
import { FormComponent } from "../../components/form/form.component";
import { LogoComponent } from "../../components/logo/logo.component";
import { RegisterModel } from "../../shared/model/register-model";
import { UserService } from "../../shared/services/user.service";
import { ErrorResponse, InputFieldError } from '../../shared/http/error-response';

@Component({
  selector: 'app-register-page',
  standalone: true,
    imports: [
        ContainerComponent,
        FormComponent,
        FormsModule,
        RouterLink,
        LogoComponent
    ],
  templateUrl: './register-page.component.html',
  styleUrl: './register-page.component.scss'
})
export class RegisterPageComponent {

    registerData: RegisterModel

    constructor(private _userService: UserService, private router: Router) {
        this.registerData = new RegisterModel()
    }

    doCreateUser() {
        const password: string = this.registerData.password;
        const rePassword: string = this.registerData.rePassword;

        if (password !== rePassword) {
            alert("Erro: As senhas não batem");
            return;
        }

        this._userService.doCreateUser(this.registerData).subscribe({
            next: () => {
                alert("Usuário criado com sucesso!");
                this.router.navigateByUrl("/login");
            },
            error: (error: ErrorResponse) => {
                alert(error.message);
            }
        });
    }
}
