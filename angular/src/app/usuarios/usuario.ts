export class Usuario {

    numct: string;
    contrasenia: string;
    nombre: string;
    paterno: string;
    materno: string;
    estaActivo: boolean;
    correo: string;
    celular: string;
    pumaPuntos: number;
    esProveedor: boolean;
    esAdministrador: boolean;

    constructor() {
        this.numct = "";
        this.contrasenia = "6bf4969808f1c9998af7809f7aa2c3783aabf8451fe5a6f9d3c3b159fb31b91d";
        this.nombre = "";
        this.paterno = "";
        this.materno = "";
        this.estaActivo = true;
        this.correo = "";
        this.celular = "";
        this.pumaPuntos = 100;
        this.esProveedor = false;
        this.esAdministrador = false;
    }
}
