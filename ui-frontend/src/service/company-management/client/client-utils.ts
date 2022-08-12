import {ClientDTO} from "../../../data/model/person/client/client-dto";

export class ClientUtils {

  public static copyClient(source: ClientDTO, target: ClientDTO) {
    target.id = source.id;
    target.firstName = source.firstName;
    target.lastName = source.lastName;
    target.comment = source.comment;
    target.photo = source.photo;
    target.gender = source.gender;
    target.phone = source.phone;
    target.creationDate = source.creationDate;
    target.origin = source.origin;
    target.technicalComment = source.technicalComment;
  }
}
