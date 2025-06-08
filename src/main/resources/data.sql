DELETE FROM proyecto;
DELETE FROM maquina;
-- Insertar algunos proyectos
INSERT INTO proyecto (id, nombre, descripcion, fechainicio, activo) VALUES
('4c2b2e6f-fdaf-442b-848b-c57f9a27a289', 'Sistema de Gestión de Ventas', 'Aplicación distribuida para gestionar ventas de productos.', '2024-11-01', true),
('5d42f003-c709-409e-bd17-0cf93b53dc4b', 'Portal Web Corporativo', 'Sitio web institucional para presentación de servicios.', '2025-01-15', true),
('6f158d34-3bc5-4a90-b8a9-0c7f227e0b17', 'Sistema de Monitoreo de Servidores', 'Aplicación para el monitoreo de infraestructuras TI.', '2025-04-01', false);
INSERT INTO proyecto (id, nombre, descripcion, fechainicio, activo) VALUES
('7a61e0d4-9b72-4a7b-870f-8f25d34d3020', 'Aplicación de Gestión de Inventario', 'Sistema para controlar el inventario de productos en almacén.', '2024-09-20', true),
('82c3b0f9-dfc5-4d60-90be-0c21f3f8d0d3', 'Plataforma de E-learning', 'Portal web para impartir cursos en línea y seguimiento de estudiantes.', '2025-02-10', true),
('9dff3cd7-f934-4c10-9c83-7b8e3b1dbe77', 'Sistema de Ticketing', 'Herramienta para registrar, asignar y dar seguimiento a incidentes técnicos.', '2024-12-05', false),
('a4c0db4f-df42-4643-981e-7ec2ad2e54ab', 'App Móvil para Clientes', 'Aplicación móvil para que los clientes consulten su historial de compras.', '2025-03-01', true),
('be7d8c6f-3f1b-4e4b-b3ab-3dc2e135b289', 'Dashboard de Analítica', 'Panel de control con gráficos e indicadores clave de rendimiento (KPI).', '2025-05-15', true),
('ccfc9433-d0ab-45fc-854e-80e6eac57c1b', 'Sistema de Gestión de Recursos Humanos', 'Plataforma para administrar nómina, vacaciones y evaluaciones.', '2025-06-10', false),
('de9f6c1a-8ab1-42aa-9890-11bca72f71dd', 'Módulo de Facturación Electrónica', 'Sistema para emitir y validar facturas electrónicas conforme a la DIAN.', '2025-01-28', true),
('ed1e3f9b-527c-48d2-9b1d-8e3c6a5c14b1', 'API para Integración Externa', 'Servicio RESTful para que terceros consuman información de productos.', '2024-10-10', true);

-- Insertar algunas máquinas
INSERT INTO maquina (id, nombre, ubicacion, tipo) VALUES
('1e7d1a80-79a4-4b35-9a88-5e6f83982fc2', 'Servidor de Producción 1', 'DataCenter Norte', 'FISICO'),
('2d93e0b5-84ef-4f13-91fb-2a7a03e24e04', 'Servidor Virtualizado', 'Cluster Kubernetes', 'VIRTUAL'),
('3ad32b62-0c3c-4c45-a0e6-7c52a4f81c4e', 'Nodo Docker 01', 'Cluster Docker Swarm', 'CONTENEDOR');
INSERT INTO maquina (id, nombre, ubicacion, tipo) VALUES
('4e8f20a7-1c6b-4a58-91a6-9b39c28782a1', 'Servidor de Desarrollo 1', 'DataCenter Sur', 'FISICO'),
('5b9d3c16-d9d7-48a1-859b-d8b0c03b56a7', 'Servidor Backup', 'DataCenter Central', 'FISICO'),
('6a5c4b90-4d3f-4426-909e-eccb546f5f1f', 'Máquina Virtual Ubuntu', 'Cluster VMware', 'VIRTUAL'),
('7c22f680-3284-4a0d-b94e-3d0e2dd9a6f2', 'Contenedor API Gateway', 'Cluster Docker Swarm', 'CONTENEDOR'),
('8e0dcf91-f7ef-4aa4-a9d8-d2a7b5e21bd4', 'Servidor de Pruebas', 'DataCenter Norte', 'FISICO'),
('90d33f22-e5ba-4e3f-91a0-8cbe3106fa52', 'VM Windows Server', 'Hyper-V Host 3', 'VIRTUAL'),
('a1e5dbbc-8e6b-47b6-b25c-b5d4d4720d3f', 'Contenedor PostgreSQL', 'Cluster Kubernetes', 'CONTENEDOR'),
('b2f4a9c1-6d6d-4e5b-8a99-2bbd9f64cf9e', 'Servidor de Monitoreo', 'DataCenter Oeste', 'FISICO');
