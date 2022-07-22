import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {backend} from "../../environments/environment";
import {WrappedProductDTO} from "../../data/company-management/saleable/product/wrapped-product-dto";
import {JwtAccount} from "../authentication/jwt-account";
import {AuthenticationService} from "../authentication/authentication.service";
import {ProductDTO} from "../../data/company-management/saleable/product/product-dto";
import {ProductCreationInformation} from "../../data/company-management/saleable/product/product-creation-information";
import {ProductUpdatedInformation} from "../../data/company-management/saleable/product/product-updated-information";

@Injectable({
  providedIn: 'root'
})
export class ProductManagementService {

  constructor(private http: HttpClient) {
    // Nothing
  }

  private static companyProductUrl(companyId: string): string {
    return backend.backendUrl + backend.apiV1Url + backend.companiesUrl + '/' + companyId + backend.productUrl;
  }

  private static companySpecificProductUrl(companyId: string, productId: string): string {
    return ProductManagementService.companyProductUrl(companyId) + '/' + productId;
  }

  public allProducts(): Promise<WrappedProductDTO[]> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<WrappedProductDTO[]>(((resolve, reject) => {
      this.http.get<WrappedProductDTO[]>(ProductManagementService.companyProductUrl(jwtAccount.companyId)).subscribe({
        next: (allProducts) => {
          resolve(allProducts);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }));
  }

  public searchProduct(filter: string): Promise<WrappedProductDTO[]> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<WrappedProductDTO[]>(((resolve, reject) => {
      this.http.get<WrappedProductDTO[]>(ProductManagementService.companyProductUrl(jwtAccount.companyId) + '?filter=' + filter).subscribe({
        next: (allProducts) => {
          resolve(allProducts);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }));
  }

  public getSpecificProduct(productId: string): Promise<ProductDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<ProductDTO>(((resolve, reject) => {
      this.http.get<ProductDTO>(ProductManagementService.companySpecificProductUrl(jwtAccount.companyId, productId)).subscribe({
        next: (product) => {
          resolve(product);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }))
  }

  public createProduct(productInformation: ProductCreationInformation): Promise<WrappedProductDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<WrappedProductDTO>(((resolve, reject) => {
      this.http.post<WrappedProductDTO>(ProductManagementService.companyProductUrl(jwtAccount.companyId), productInformation).subscribe({
        next: (product) => {
          resolve(product);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }))
  }

  public updateProduct(productId: string, productUpdates: ProductUpdatedInformation): Promise<WrappedProductDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<WrappedProductDTO>(((resolve, reject) => {
      this.http.put<WrappedProductDTO>(ProductManagementService.companySpecificProductUrl(jwtAccount.companyId, productId), productUpdates).subscribe({
        next: (product) => {
          resolve(product);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }))
  }
}
