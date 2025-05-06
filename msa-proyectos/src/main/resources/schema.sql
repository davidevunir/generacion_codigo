CREATE TABLE IF NOT EXISTS maquina (
    id UUID PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    ubicacion VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL
);
CREATE TABLE IF NOT EXISTS proyecto (
    id UUID PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    fechainicio DATE,
    activo BOOLEAN
);
