import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ClientDTO} from "../../../../data/company-management/person/client/client-dto";
import {ClientInformationCreation} from "../../../../data/company-management/person/client/client-information-creation";
import {ClientUpdatedInformation} from "../../../../data/company-management/person/client/client-updated-information";

export class ClientForm extends FormGroup {

  constructor(withValidators = false, client?: ClientDTO) {
    super({
      email: new FormControl(client?.email, withValidators ? [Validators.required] : []),
      firstName: new FormControl(client?.firstName, withValidators ? [Validators.required] : []),
      lastName: new FormControl(client?.lastName, withValidators ? [Validators.required] : []),
      comment: new FormControl(client?.comment),
      gender: new FormControl(client?.gender),
      phone: new FormControl(client?.phone),
      origin: new FormControl(client?.origin),
      technicalComment: new FormControl(client?.technicalComment)
    });
  }

  public extractClientInformation(): ClientInformationCreation | ClientUpdatedInformation {
    const v = this.value;
    return {
      email: v.email,
      firstName: v.firstName,
      lastName: v.lastName,
      comment: v.comment,
      gender: v.gender,
      phone: v.phone,
      origin: v.origin,
      technicalComment: v.technicalComment
    }
  }
}
