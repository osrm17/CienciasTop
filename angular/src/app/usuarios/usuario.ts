export class Usuario {

    numct: String;
    contrasenia: String;
    nombre: String;
    paterno: String;
    materno: String;
    estaActivo: boolean;
    correo: String;
    celular: String;
    pumaPuntos: number;
    esProveedor: boolean;
    esAdministrador: boolean;

    constructor() {
        this.numct = "";
        this.contrasenia = "";
        this.nombre = "";
        this.paterno = "";
        this.materno = "";
        this.estaActivo = true;
        this.correo = "";
        this.celular = "";
        this.pumaPuntos = 0;
        this.esProveedor = true;
        this.esAdministrador = true;
    }

}
