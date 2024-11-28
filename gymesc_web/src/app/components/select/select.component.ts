import { Component, forwardRef, Input } from '@angular/core';
import { ControlValueAccessor, FormsModule, NG_VALUE_ACCESSOR } from "@angular/forms";
import { NgForOf } from "@angular/common";

@Component({
    selector: 'app-select',
    standalone: true,
    imports: [
        FormsModule,
        NgForOf
    ],
    templateUrl: './select.component.html',
    styleUrl: './select.component.scss',
    providers: [{
        provide: NG_VALUE_ACCESSOR,
        useExisting: forwardRef(() => SelectComponent),
        multi: true,
    }]
})
export class SelectComponent implements ControlValueAccessor {

    // @ts-ignore
    @Input() dataList: any[];

    // @ts-ignore
    @Input() buildDefaultItem: (value: string) => any;

    // @ts-ignore
    @Input() listItemConfig: ListItemConfig;

    value: string = "";

    currentItem: any;

    private onChange: (item: any) => void = () => {};
    private onTouched: () => void = () => {};

    writeValue(value: any): void {
        if (!value) {
            this.value = "";
        } else if (value instanceof String) {
            this.value = value.toString();
        } else {
            this.value = value[this.listItemConfig.value];
        }
    }

    registerOnChange(fn?: any): void {
        this.onChange = fn;
    }

    registerOnTouched(fn?: any): void {
        this.onTouched = fn;
    }

    setDisabledState?(isDisabled: boolean): void {}

    onInput(value: string) {
        this.value = value;

        this.currentItem = this.dataList.find((item: any) => {
            return item[this.listItemConfig.value] === value;
        }) || this.buildDefaultItem(value);


        this.onChange(this.currentItem);
    }
}

export class ListItemConfig {

    id: string;

    value: string;

    constructor(id: string, value: string) {
        this.id = id;
        this.value = value;
    }

}
