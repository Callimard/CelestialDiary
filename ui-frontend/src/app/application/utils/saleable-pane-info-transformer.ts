import {PaneInfoTransformer, PaneInfoWithId} from "../../library/informative/info-pane/info-pane.component";
import {WrappedSaleableDTO} from "../../../data/company-management/saleable/wrapped-saleable-dto";

export class SaleablePaneInfoTransformer implements PaneInfoTransformer<WrappedSaleableDTO> {
  transform(saleable: WrappedSaleableDTO): PaneInfoWithId {
    return {
      id: saleable.id,
      title: saleable.name,
      subTitle: saleable.suggestedPrice + ' €'
    };
  }
}
