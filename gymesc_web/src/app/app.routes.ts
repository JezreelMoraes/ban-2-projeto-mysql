import { Routes } from '@angular/router';
import { HomePageComponent } from "./pages/home/home-page.component";
import { LoginPageComponent } from "./pages/login/login-page.component";
import { RegisterPageComponent } from "./pages/register/register-page.component";
import { ErrorPageComponent } from "./pages/error/error-page.component";
import { ApplicationLayoutComponent } from "./components/layout/application-layout/application-layout.component";
import { ExercisesPageComponent } from "./pages/exercises/exercises-page.component";
import { WorkoutsPageComponent } from "./pages/workouts/workouts-page.component";
import { SettingsPageComponent } from "./pages/settings/settings-page.component";
import { ExercisesAddPageComponent } from "./pages/exercises/add/exercises-add-page.component";
import { WorkoutsAddPageComponent } from './pages/workouts/add/workouts-add-page.component';
import { ExercisesEditPageComponent } from './pages/exercises/edit/exercises-edit-page.component';
import { WorkoutsEditPageComponent } from './pages/workouts/edit/workouts-edit-page.component';

export const routes: Routes = [
    {
        path: "app", component: ApplicationLayoutComponent, children: [
            { path: "home", title: "GymEsc | Menu principal", component: HomePageComponent },
            {
                path: "exercises", children: [
                    { path: "", title: "GymEsc | Exercícios", component: ExercisesPageComponent },
                    { path: "add", title: "GymEsc | Criar exercício", component: ExercisesAddPageComponent },
                    { path: "edit/:id", title: "GymEsc | Editar exercício", component: ExercisesEditPageComponent }
                ]
            },
            {
                path: "workouts", children: [
                    { path: "", title: "GymEsc | Treinos", component: WorkoutsPageComponent },
                    { path: "add", title: "GymEsc | Criar treino", component: WorkoutsAddPageComponent },
                    { path: "edit/:id", title: "GymEsc | Editar treino", component: WorkoutsEditPageComponent }
                ]
            },
            { path: "settings", title: "GymEsc | Configurações", component: SettingsPageComponent },
        ]
    },
    { path: "login", title: "GymEsc | Login", component: LoginPageComponent },
    { path: "register", title: "GymEsc | Cadastro", component: RegisterPageComponent },
    { path: "error", title: "GymEsc | Erro", component: ErrorPageComponent },
    { path: "**", redirectTo: "/app/home" },
];
