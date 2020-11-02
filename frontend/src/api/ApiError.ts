
export class ApiError<T> implements Error{

    public name: string;
    public httpStatus: number;
    public message: string;
    public data: T;

    constructor(httpStatus:number, message: string, data: T){
        this.name = "ApiError";
        this.httpStatus = httpStatus;
        this.message = message;
        this.data = data;
    }

}