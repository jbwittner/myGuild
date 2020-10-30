
export class ApiError<T> implements Error{

    public name: string;

    constructor(public httpStatus:number, public message: string, public data: T){
        this.name = "ApiError";
    }

}