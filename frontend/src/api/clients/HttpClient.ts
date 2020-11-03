import Axios, { AxiosError, AxiosResponse } from "axios";
import { ApiError } from "./../ApiError";

export class HttpClient {

    public static readonly FOUND_STATUS = 403;
    public static readonly UNAUTHORIZED_ERROR_STATUS = 401;
    public static readonly FORBIDDEN_ERROR_STATUS = 403;
    public static readonly INTERNAL_SERVER_ERROR_STATUS = 501;
    public static readonly INTERNAL_SERVER_ERROR_MESSAGE = "An internal server error occured";

    private redirectIfUnauthorized: boolean;

    constructor(redirectIfUnauthorized: boolean = true){
        this.redirectIfUnauthorized = redirectIfUnauthorized;
    }

    protected async get(apiUrl: string, queryParams?: {[key: string]: any}, headers?: {[key: string]: any}){
        return await this.manageErrors(() =>
            Axios.get(apiUrl, {
                params: queryParams,
                headers: this.buildHeader(headers)
            })
        );
    }

    protected async post(apiUrl: string, data: any, headers?: {[key: string]: any}){
        return await this.manageErrors(() =>
            Axios.post(apiUrl, data, {
                headers: this.buildHeader(headers)
            })
        )
    }

    private buildHeader(headers?: {[key: string]: any}): {[key: string]: any} {
        return Object.assign({
            "Content-Type": "application/json"
        }, headers);
    }

    private async manageErrors(apiCall: () => Promise<AxiosResponse>): Promise<AxiosResponse> {
        try {
            const response: AxiosResponse = await apiCall();
            return response;
        } catch (err) {
            const error: AxiosError = err as AxiosError;

            if(error.response){
                if((this.redirectIfUnauthorized === true) && (error.response.status === HttpClient.UNAUTHORIZED_ERROR_STATUS)){
                    window.location.href = "/";
                }
                throw new ApiError(error.response.status, error.response.statusText, error.response.data)
            } else if (error.request){
                throw new ApiError(HttpClient.INTERNAL_SERVER_ERROR_STATUS, HttpClient.INTERNAL_SERVER_ERROR_MESSAGE, error.request)
            } else {
                throw new ApiError(HttpClient.UNAUTHORIZED_ERROR_STATUS, error.message, null)
            }
        }
    }

}