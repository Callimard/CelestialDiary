import {FormGroup} from "@angular/forms";

export class PrivilegeFormGroup extends FormGroup {
    constructor() {
        super({});
    }

    public extractPrivileges(): string[] {
        let privileges: string[] = [];
        const controlKeys = Object.keys(this.controls);
        const v = this.value;
        for (let key of controlKeys) {
            if (v[key] === true) {
                privileges.push(key);
            }
        }
        return privileges;
    }
}
