/*
 * 
 * Responsable:
 * Carlos Antonio González Canario
 *
 */

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


CREATE DATABASE `gymmanagerv5` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `gymmanagerv5`;

DELIMITER $$
CREATE  PROCEDURE `usp_buscarEmpleado`(in filtroBusqueda varchar(30), in campoBusqueda int)
begin
    if campoBusqueda = 2 then
        select Empleados.idEmpleado,
               Empleados.puesto,
               Empleados.salario,
               Empleados.fechaIngreso,
               Personas.*
        from Personas
            inner join Empleados
            on Personas.idPersona = Empleados.idPersona
        Where Personas.cedula = filtroBusqueda;
    elseif campoBusqueda = 3 then
        select Empleados.idEmpleado,
               Empleados.puesto,
               Empleados.salario,
               Empleados.fechaIngreso,
               Personas.*
        from Personas
            inner join Empleados
            on Personas.idPersona = Empleados.idPersona
        Where Personas.nombre = filtroBusqueda;
    elseif campoBusqueda = 4 then
        select Empleados.idEmpleado,
               Empleados.puesto,
               Empleados.salario,
               Empleados.fechaIngreso,
               Personas.*
        from Personas
            inner join Empleados
            on Personas.idPersona = Empleados.idPersona
        Where Personas.apellido = filtroBusqueda;
    end if;
    
end$$

CREATE  PROCEDURE `usp_buscarSeccionesSocio`(in idsoc int)
begin
    
    select * from vsecciones
    where idSeccion in ( select idSeccion from secciones_socio where idSocio = idsoc);
end$$

CREATE  PROCEDURE `usp_buscarSocio`(in filtroBusqueda varchar(30), in campoBusqueda int)
begin
    if campoBusqueda = 2 then
        select Socios.*,
               Personas.*
        from Socios
            inner join Personas
                on Socios.idPersona = Personas.idPersonaa
        Where Personas.cedula = filtroBusqueda;
    elseif campoBusqueda = 3 then
        select Socios.*,
               Personas.*
        from Socios
            inner join Personas
                on Socios.idPersona = Personas.idPersona
            inner join Membresias
                on Socios.idMembresia = Membresias.idMembresia
        Where Personas.nombre = filtroBusqueda;
    elseif campoBusqueda = 4 then
        select Socios.*,
               Personas.*
        from Socios
            inner join Personas
                on Socios.idPersona = Personas.idPersona
            inner join Membresias
                on Socios.idMembresia = Membresias.idMembresia
        Where Personas.apellido = filtroBusqueda;
    elseif campoBusqueda = 5 then
        select Socios.*,
               Personas.*
        from Socios
            inner join Personas
                on Socios.idPersona = Personas.idPersona
            inner join Membresias
                on Socios.idMembresia = Membresias.idMembresia
        where Membresias.nombre = filtroBusqueda;
    end if;
    
end$$

CREATE  PROCEDURE `usp_buscarTelefonos`(idPer int )
begin
    Select idTelefono,
           numero,
           tipo
    From telefonos
    Where idPersona = idPer;
end$$

CREATE  PROCEDURE `usp_findSecsByInstructor`( in  nombreInstructor varchar(50))
begin
    select Secciones.idSeccion,
           Secciones.nombre as nombreSeccion,
           Secciones.dia,
           Secciones.horaInicio,
           Secciones.horaFin,
           Secciones.capacidad,
           Secciones.idInstructor,
           Clases.idClase,
           Clases.nombre as nombreClase,
           Clases.descripcion,
           Clases.precio,
           Salones.idSalon,
           Salones.nombre as nombreSalon, 
           concat(vInstructores.nombre, ' ', vInstructores.apellido) as nombreInstructor
    From Secciones
        inner join Clases
            on Secciones.idClase = Clases.idClase
        inner join Salones
            on Secciones.idSalon = Salones.idSalon
        inner join vInstructores
            on Secciones.idInstructor = vInstructores.idInstructor
    where concat(vInstructores.nombre, ' ', vInstructores.apellido) like concat('%',nombreInstructor,'%');

end$$

CREATE  PROCEDURE `usp_generarFactura`(in idsoc int, in idMemb int, in tipomemb varchar(30))
BEGIN
    declare hecho int default 0;
    declare idseccion, idfact, idfactura, intervalo int;
    declare valor, pMembresia double;
    declare nombreSeccion, nombreMem varchar(30);
    declare detalle, detalleMem varchar(60);
    declare fechafact date;

    declare continue handler for sqlstate '02000' set hecho = 1;
        
    case tipomemb
        when 'Diario' then set intervalo = 1;
        when 'Semanal' then set intervalo = 7;
        when 'Quincenal' then set intervalo = 15;
        when 'Mensual' then set intervalo = 30;
        when 'Anual' then set intervalo = 360;
    end case;
    
    select precio, nombre into pMembresia, nombreMem
    from gymmanagerv5.Membresias
    where idMembresia = idMemb;
    
    set detalleMem = 'Membresia ' + nombreMem + ' de tipo pago ' + tipomembresia; 
        
    select  idFactura, fechaCreacion into idfact, fechafact 
    from gymmanagerv5.Facturas
    where idSocio = idsocio
    order by fechaFactura desc
    limit 1;
    
    start transaction;
    
    if isnull(fechafact) then

        insert into gymmanagerv5.facturas(idFactura, idSocio)
        values(null, idsoc);
        
        select max(idFactura) into idfactura
        from gymmanagerv5.facturas
        where idSocio = idsoc;
        
        insert into gymmanagerv5.DetalleFactura
        values(idfactura, null, deetalleMem, pMembresia);
    
    elseif ( now() - fechafact ) >= intervalo then
        
    
        insert into gymmanagerv5.facturas(idFactura, idSocio)
        values(null, idsoc);
        
        select max(idFactura) into idfactura
        from gymmanagerv5.facturas
        where idSocio = idsoc;
        
        insert into gymmanagerv5.DetalleFactura
        values(idfactura, null, deetalleMem, pMembresia);
        
        
        commit;
        
    end if;
    
END$$

CREATE  PROCEDURE `usp_generarFacturaDiaria`(in idsoc int)
begin
    declare nombreMem varchar(30) default 'N/A';
    declare detalleM text default 'N/A';
    declare pMembresia double default 0;
    declare idfact, iddet, idmemb int;
    
    select idMembresia into idmemb from socios where idSocio = idsoc;
    
    if idmemb in (Select idMembresia from membresias where tipo = 'Diario') then
    
        select Membresias.precio, Membresias.nombre into pMembresia, nombreMem
        from gymmanagerv5.Membresias
        where membresias.idMembresia = idmemb;
        
        set detalleM = concat('Membresia ' , nombreMem , ' de tipo pago Diario');
        
        select max(idFactura) into idfact
        from gymmanagerv5.facturas;
        
        insert into gymmanagerv5.facturas(idFactura, idSocio)
        values(idfact+1, idsoc);
        
        select max(idFactura) into idfact
        from gymmanagerv5.facturas
        where idSocio = idsocio;
        
        select max(idDetalleFactura) into iddet
        from detallefactura where idFactura = idfact;
            
        insert into gymmanagerv5.DetalleFactura
        values(idfact, iddet+1, detalleM, pMembresia);
    
    end if;

end$$

CREATE  PROCEDURE `usp_generarFacturas`()
begin
    declare hecho int default 0;
    
    declare idsocio, idmembresia int;
    declare tipomembresia varchar(30);
    
    declare socios cursor for Select idSocio, idMembresia from socios where idMembresia not like (select idMembresia from membresias where tipo = 'Diario');
    declare continue handler for sqlstate '02000' set hecho = 1;
    
    open socios;
    
    repeat 
        fetch socios into idsocio, idmembresia; 
        select tipo into tipomembresia from Membresias where idMembresia = idmembresia;
        call usp_generarFactura(idsocio, idmembresia, tipomembresia);
    until hecho end repeat;
    
    close socios;
end$$

CREATE  PROCEDURE `usp_obtenerFacturasPend`(in idsoc int)
begin
    select *
    from facturas
    where idFactura not in (select idFactura from pagos) and idSocio = idsoc
    order by fechaCreacion desc;
end$$

CREATE  PROCEDURE `usp_registrarPago`(idFactura int, monto double)
begin
    insert into pagos
    values(null, monto, idFactura, null);

end$$

CREATE  FUNCTION `udf_generarNCF`() RETURNS varchar(20) CHARSET utf8
begin
    declare result varchar(20);
    declare secuencia varchar(10) default 'A010010010';
    declare inicio int default 10000000;
    declare maxfact int;

    select max(idFactura) into maxfact from facturas;
    
    if isnull(maxfact) then
        set result = concat(secuencia, inicio+1);
    else
        set result = concat(secuencia, cast((inicio+maxfact+1) as char));
    end if;

    return result;

end$$

DELIMITER ;

CREATE TABLE IF NOT EXISTS `asistencia_socios` (
  `idSocio` int(11) NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`idSocio`,`fecha`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `asistencia_socios` (`idSocio`, `fecha`) VALUES
(2, '2012-04-28'),
(2, '2012-05-03'),
(2, '2012-05-06'),
(3, '2012-05-06');

DELIMITER $$
CREATE TRIGGER `udt_addFactura` BEFORE INSERT ON `asistencia_socios`
 FOR EACH ROW begin
    call usp_generarFacturaDiaria(New.idSocio);
end
$$
DELIMITER ;

CREATE TABLE IF NOT EXISTS `clases` (
    `idClase` int(11) NOT NULL AUTO_INCREMENT,
    `nombre` varchar(30) NOT NULL,
    `descripcion` text NOT NULL,
    `precio` double NOT NULL,
    PRIMARY KEY (`idClase`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

INSERT INTO `clases` (`idClase`, `nombre`, `descripcion`, `precio`) VALUES
(2, 'alterofilia', 'clase de alterofilia para mujeres', 250);

CREATE TABLE IF NOT EXISTS `detallefactura` (
    `idFactura` int(11) NOT NULL,
    `idDetalleFactura` int(11) NOT NULL AUTO_INCREMENT,
    `detalle` text NOT NULL,
    `precio` double NOT NULL,
    PRIMARY KEY (`idDetalleFactura`,`idFactura`),
    KEY `fk_DetalleFactura_facturas1` (`idFactura`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=22 ;

INSERT INTO `detallefactura` (`idFactura`, `idDetalleFactura`, `detalle`, `precio`) VALUES
(1, 1, 'Inscripcion de seccion', 250),
(1, 4, 'Inscripcion de seccion', 250),
(4, 6, 'Inscripcion de seccion alterofilia', 250),
(5, 7, 'Inscripcion de seccion alterofilia', 250),
(5, 8, 'Inscripcion de seccion alterofilia', 250),
(5, 9, 'Inscripcion de seccion alterofilia', 250),
(5, 10, 'Inscripcion de seccion alterofilia', 250),
(6, 11, 'Inscripcion de seccion alterofilia', 250),
(7, 12, 'Inscripcion de seccion alterofilia', 250),
(8, 13, 'Inscripcion de seccion alterofilia', 250),
(9, 14, 'Inscripcion de seccion alterofilia', 250),
(10, 15, 'Inscripcion de seccion alterofilia', 250),
(19, 17, 'Inscripcion de seccion alterofilia', 250),
(20, 18, 'Inscripcion de seccion alterofilia', 250),
(21, 21, 'Membresia Diario de tipo pago Diario', 50);

CREATE TABLE IF NOT EXISTS `direcciones` (
    `idDireccion` int(11) NOT NULL AUTO_INCREMENT,
    `municipio` varchar(30) NOT NULL,
    `sector` varchar(30) NOT NULL,
    `calle` varchar(30) NOT NULL,
    `numero` int(11) NOT NULL,
    PRIMARY KEY (`idDireccion`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=35 ;

INSERT INTO `direcciones` (`idDireccion`, `municipio`, `sector`, `calle`, `numero`) VALUES
(9, 'Santo Domingo Este', 'Los Mameyes', 'Sanchez', 25),
(11, 'Santo Domingo', 'Bella Vista', 'La Real', 23),
(12, 'Santo Domingo Norte', 'Villa Mella', 'la 42', 33),
(13, 'Santo Domingo Norte', 'Villa Mella', '3ra', 32),
(14, 'Distrito Nacional', 'Bella Vista', 'Romulo Betancourt', 21),
(15, 'Santo Domingo Este', 'El Ureña', 'que se yo', 20),
(16, 'Distrito Nacional', 'Zona Universitaria', 'Tiradentes', 302),
(17, 'Santo Domingo Este', 'Villas del Este', '12', 21);

CREATE TABLE IF NOT EXISTS `empleados` (
    `idEmpleado` int(11) NOT NULL AUTO_INCREMENT,
    `puesto` varchar(30) NOT NULL,
    `salario` double NOT NULL,
    `fechaIngreso` date DEFAULT NULL,
    `idPersona` int(11) NOT NULL,
    PRIMARY KEY (`idEmpleado`),
    KEY `fk_empleados_personas1` (`idPersona`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

INSERT INTO `empleados` (`idEmpleado`, `puesto`, `salario`, `fechaIngreso`, `idPersona`) VALUES
(6, 'Emp. Registro', 32000, '2012-04-22', 2),
(7, 'Instructor', 22000, '2012-04-24', 5),
(8, 'Emp. Recursos Humanos', 25000, '2012-04-29', 6),
(9, 'Cajero', 25000, '2012-04-29', 7),
(10, 'Recepcionista', 25000, '2012-04-29', 8),
(11, 'Gerente', 90000, '2012-04-29', 9);

DELIMITER $$
CREATE TRIGGER `udt_defaultFechaEmp` BEFORE INSERT ON `empleados`
 FOR EACH ROW begin
    set New.fechaIngreso = Now();
end
$$
DELIMITER ;

CREATE TABLE IF NOT EXISTS `facturas` (
    `idFactura` int(11) NOT NULL AUTO_INCREMENT,
    `nfc` varchar(20) DEFAULT NULL,
    `fechaCreacion` date DEFAULT NULL,
    `idEmpleado` int(11) DEFAULT NULL,
    `idSocio` int(11) NOT NULL,
    PRIMARY KEY (`idFactura`),
    KEY `fk_Facturas_Empleados1` (`idEmpleado`),
    KEY `fk_facturas_socios1` (`idSocio`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=23 ;

INSERT INTO `facturas` (`idFactura`, `nfc`, `fechaCreacion`, `idEmpleado`, `idSocio`) VALUES
(1, 'A01001001010000001', '2012-05-05', 9, 2),
(4, 'A01001001010000003', '2012-05-05', 9, 2),
(5, 'A01001001010000004', '2012-05-05', 9, 2),
(6, 'A01001001010000005', '2012-05-05', 9, 2),
(7, 'A01001001010000006', '2012-05-05', 9, 2),
(8, 'A01001001010000007', '2012-05-05', 9, 2),
(9, 'A01001001010000008', '2012-05-05', 9, 2),
(10, 'A01001001010000009', '2012-05-05', 9, 2),
(19, 'A01001001010000011', '2012-05-06', 9, 2),
(20, 'A01001001010000020', '2012-05-06', 9, 3),
(21, 'A01001001010000021', '2012-05-06', 9, 3);

DELIMITER $$
CREATE TRIGGER `udt_defaultFechaFactura` BEFORE INSERT ON `facturas`
 FOR EACH ROW begin
    declare actual varchar(20);
    
    set New.fechaCreacion = Now();
    
    select udf_generarNCF() into actual;
    
    set New.nfc = actual;
        
end
$$
DELIMITER ;

CREATE TABLE IF NOT EXISTS `instructores` (
    `idInstructor` int(11) NOT NULL AUTO_INCREMENT,
    `especialidad` varchar(30) NOT NULL,
    `idEmpleado` int(11) NOT NULL,
    PRIMARY KEY (`idInstructor`),
    KEY `fk_Instructores_Empleados1` (`idEmpleado`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

INSERT INTO `instructores` (`idInstructor`, `especialidad`, `idEmpleado`) VALUES
(1, 'Alterofilia', 7);

CREATE TABLE IF NOT EXISTS `membresias` (
    `idMembresia` int(11) NOT NULL AUTO_INCREMENT,
    `nombre` varchar(30) NOT NULL,
    `descripcion` text NOT NULL,
    `tipo` varchar(30) NOT NULL,
    `precio` double NOT NULL,
    PRIMARY KEY (`idMembresia`),
    UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

INSERT INTO `membresias` (`idMembresia`, `nombre`, `descripcion`, `tipo`, `precio`) VALUES
(1, 'Diario', 'membresia de pago diario', 'Diario', 50),
(2, 'Semanal', 'membresia de pago semanal', 'Semanal', 320),
(3, 'Quincenal', 'membresia de pago quincenal', 'Quincenal', 600),
(4, 'Mensual', 'membresia de pago mensual', 'Mensual', 1150),
(5, 'Anual', 'membresia de pago anual', 'Anual', 12000);

CREATE TABLE IF NOT EXISTS `pagos` (
    `idPago` int(11) NOT NULL AUTO_INCREMENT,
    `monto` double NOT NULL,
    `idFactura` int(11) NOT NULL,
    `fechaPago` date DEFAULT NULL,
    PRIMARY KEY (`idPago`),
    KEY `fk_pagos_Facturas1` (`idFactura`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=26 ;

INSERT INTO `pagos` (`idPago`, `monto`, `idFactura`, `fechaPago`) VALUES
(1, 501, 1, '2012-05-05'),
(2, 250, 4, '2012-05-05'),
(3, 1000, 5, '2012-05-05'),
(4, 250, 6, '2012-05-05'),
(5, 250, 7, '2012-05-05'),
(6, 250, 8, '2012-05-05'),
(7, 250, 9, '2012-05-05'),
(10, 250, 10, '2012-05-06'),
(11, 250, 19, '2012-05-06'),
(16, 250, 20, '2012-05-06'),
(25, 50, 21, '2012-05-06');

DELIMITER $$
CREATE TRIGGER `udt_defaultFechaPago` BEFORE INSERT ON `pagos`
 FOR EACH ROW begin
    set New.fechaPago = Now();
end
$$
DELIMITER ;

CREATE TABLE IF NOT EXISTS `persadministrativo` (
    `idEmpleado` int(11) NOT NULL,
    `userName` varchar(20) NOT NULL,
    `password` varchar(30) NOT NULL,
    PRIMARY KEY (`idEmpleado`),
    UNIQUE KEY `userName_UNIQUE` (`userName`),
    KEY `fk_Credenciales_Empleados1` (`idEmpleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `persadministrativo` (`idEmpleado`, `userName`, `password`) VALUES
(6, 'carlos02', '020890'),
(8, 'andy01', 'fuckyou'),
(9, 'shs007', 'shs007'),
(10, 'luiselias01', 'luiselias01'),
(11, 'ferreiras54', 'ferreiras54');

CREATE TABLE IF NOT EXISTS `personas` (
    `idPersona` int(11) NOT NULL AUTO_INCREMENT,
    `cedula` varchar(11) NOT NULL,
    `nombre` varchar(30) NOT NULL,
    `apellido` varchar(30) NOT NULL,
    `sexo` char(1) NOT NULL,
    `fechaNacimiento` date NOT NULL,
    `idDireccion` int(11) NOT NULL,
    PRIMARY KEY (`idPersona`),
    KEY `fk_Personas_Direcciones1` (`idDireccion`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=28 ;

INSERT INTO `personas` (`idPersona`, `cedula`, `nombre`, `apellido`, `sexo`, `fechaNacimiento`, `idDireccion`) VALUES
(2, '22301087874', 'Carlos', 'Gonzalez', 'M', '1991-09-14', 9),
(4, '00146289137', 'Jose', 'Garcia', 'M', '1972-06-03', 11),
(5, '55478965412', 'Jose', 'Jose', 'M', '2012-04-13', 12),
(6, '00123456987', 'Andres', 'Frias', 'M', '2012-04-19', 13),
(7, '00123454623', 'Saul', 'Hernandez', 'M', '1970-04-17', 14),
(8, '00123454623', 'Luis', 'Gonzalez', 'M', '1988-10-26', 15),
(9, '00123456789', 'Felix', 'Ferreiras', 'M', '1956-06-12', 16),
(10, '00111000111', 'Pedro', 'Picapiedra', 'M', '1980-09-24', 17);

CREATE TABLE IF NOT EXISTS `salones` (
    `idSalon` int(11) NOT NULL AUTO_INCREMENT,
    `nombre` varchar(30) NOT NULL,
    PRIMARY KEY (`idSalon`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

INSERT INTO `salones` (`idSalon`, `nombre`) VALUES
(1, '1-A'),
(2, '1-B'),
(3, '1-C'),
(4, '2-A'),
(5, '2-B'),
(6, '2-C');

CREATE TABLE IF NOT EXISTS `secciones` (
    `idSeccion` int(11) NOT NULL AUTO_INCREMENT,
    `nombre` varchar(30) NOT NULL,
    `dia` int(11) NOT NULL,
    `horaInicio` int(11) NOT NULL,
    `horaFin` int(11) NOT NULL,
    `capacidad` int(11) NOT NULL,
    `idClase` int(11) NOT NULL,
    `idSalon` int(11) NOT NULL,
    `idInstructor` int(11) NOT NULL,
    `fechaInicio` date NOT NULL,
    `fechaFin` date NOT NULL,
    PRIMARY KEY (`idSeccion`),
    KEY `fk_Secciones_Clases1` (`idClase`),
    KEY `fk_Secciones_Salones1` (`idSalon`),
    KEY `fk_Secciones_Instructores1` (`idInstructor`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

INSERT INTO `secciones` (`idSeccion`, `nombre`, `dia`, `horaInicio`, `horaFin`, `capacidad`, `idClase`, `idSalon`, `idInstructor`, `fechaInicio`, `fechaFin`) VALUES
(7, 'altero', 3, 7, 9, 10, 2, 1, 1, '2012-05-12', '2012-06-28'),
(8, 'altero 1c', 5, 7, 10, 29, 2, 3, 1, '2012-05-19', '2012-05-30');

CREATE TABLE IF NOT EXISTS `secciones_socio` (
    `idSocio` int(11) NOT NULL,
    `idSeccion` int(11) NOT NULL,
    `fechaInscripcion` date DEFAULT NULL,
    PRIMARY KEY (`idSocio`,`idSeccion`),
    KEY `fk_Secciones_Socio_Socios1` (`idSocio`),
    KEY `fk_Secciones_Socio_Secciones1` (`idSeccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `secciones_socio` (`idSocio`, `idSeccion`, `fechaInscripcion`) VALUES
(2, 7, '2012-05-06'),
(3, 8, '2012-05-06');

DELIMITER $$
CREATE TRIGGER `defaultFechaInscripcion` BEFORE INSERT ON `secciones_socio`
 FOR EACH ROW begin
    declare idfact int;
    declare costo double;
    declare nClase varchar(30);
    
    set New.fechaInscripcion = now();

    update Secciones
    set capacidad = capacidad - 1
    where idSeccion = New.idSeccion;
        
    select max(idFactura) into idfact from facturas
    where idFactura not in ( select idFactura from pagos) and idSocio = New.idSocio
    order by fechaCreacion desc
    limit 1;
    
    select precio, nombreClase into costo, nClase from vSecciones where idSeccion = New.idSeccion;
    
    if isnull(idfact) then
        insert into facturas(idSocio)
        values(New.idSocio);
        
        select max(idFactura) into idfact from facturas where idSocio = New.idSocio;
        
        
        insert into detallefactura
        values(idfact, null, concat('Inscripcion de seccion ', nClase), costo);
    else
        insert into detallefactura
        values(idfact, null, concat('Inscripcion de seccion ', nClase), costo);
        
    
    end if;

end
$$
DELIMITER ;

DELIMITER $$

CREATE TRIGGER `retSocio` BEFORE DELETE ON `secciones_socio`
FOR EACH ROW
begin
    update secciones
    set capacidad = capacidad + 1
    where idSeccion = Old.idseccion;
end
$$

DELIMITER ;

CREATE TABLE IF NOT EXISTS `socios` (
    `idSocio` int(11) NOT NULL AUTO_INCREMENT,
    `fechaIngreso` date DEFAULT NULL,
    `peso` double NOT NULL,
    `estatura` double NOT NULL,
    `idMembresia` int(11) NOT NULL,
    `idPersona` int(11) NOT NULL,
    `status` char(1) DEFAULT 'V',
    PRIMARY KEY (`idSocio`),
    KEY `fk_Socios_Membresias1` (`idMembresia`),
    KEY `fk_socios_personas1` (`idPersona`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

INSERT INTO `socios` (`idSocio`, `fechaIngreso`, `peso`, `estatura`, `idMembresia`, `idPersona`, `status`) VALUES
(2, '2012-04-22', 230, 5.11, 4, 4, 'A'),
(3, '2012-05-06', 210, 6.01, 1, 10, 'A');

DELIMITER $$
CREATE TRIGGER `udt_defaultFechaSocio` BEFORE INSERT ON `socios`
 FOR EACH ROW begin
    declare detalleM, nombreMem varchar(30);
    declare pMembresia double;
    declare idfactura int;
    

    set New.fechaIngreso = Now();
    set New.status = 'V';

    
end
$$
DELIMITER ;

CREATE TABLE IF NOT EXISTS `telefonos` (
    `idTelefono` int(11) NOT NULL AUTO_INCREMENT,
    `numero` varchar(10) NOT NULL,
    `tipo` varchar(20) NOT NULL,
    `idPersona` int(11) NOT NULL,
    PRIMARY KEY (`idTelefono`),
    UNIQUE KEY `nummero_UNIQUE` (`numero`),
    KEY `fk_telefonos_personas1` (`idPersona`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=72 ;

INSERT INTO `telefonos` (`idTelefono`, `numero`, `tipo`, `idPersona`) VALUES
(45, '8297279607', 'Celular', 2),
(46, '8096020572', 'Celular', 2),
(48, '8296364871', 'Celular', 4),
(49, '8694563216', 'Celular', 5),
(50, '8095642879', 'Celular', 6),
(51, '8293214567', 'Celular', 7),
(52, '8092328879', 'Celular', 8),
(53, '8092314685', 'Celular', 9),
(54, '8795463120', 'Celular', 10);

CREATE TABLE IF NOT EXISTS `vadministrativos` (
    `idPersona` int(11)
    ,`cedula` varchar(11)
    ,`nombre` varchar(30)
    ,`apellido` varchar(30)
    ,`sexo` char(1)
    ,`fechaNacimiento` date
    ,`idDireccion` int(11)
    ,`idEmpleado` int(11)
    ,`userName` varchar(20)
    ,`password` varchar(30)
    ,`puesto` varchar(30)
    ,`salario` double
    ,`fechaIngreso` date
);

CREATE TABLE IF NOT EXISTS `vempleados` (
    `idEmpleado` int(11)
    ,`puesto` varchar(30)
    ,`salario` double
    ,`fechaIngreso` date
    ,`idPersona` int(11)
    ,`cedula` varchar(11)
    ,`nombre` varchar(30)
    ,`apellido` varchar(30)
    ,`sexo` char(1)
    ,`fechaNacimiento` date
    ,`idDireccion` int(11)
);

CREATE TABLE IF NOT EXISTS `vinstructores` (
    `idPersona` int(11)
    ,`cedula` varchar(11)
    ,`nombre` varchar(30)
    ,`apellido` varchar(30)
    ,`sexo` char(1)
    ,`fechaNacimiento` date
    ,`idDireccion` int(11)
    ,`idInstructor` int(11)
    ,`especialidad` varchar(30)
    ,`idEmpleado` int(11)
    ,`puesto` varchar(30)
    ,`salario` double
    ,`fechaIngreso` date
);

CREATE TABLE IF NOT EXISTS `vseccion_socios` (
    `idSocio` int(11)
    ,`fechaIngreso` date
    ,`peso` double
    ,`estatura` double
    ,`idMembresia` int(11)
    ,`idPersona` int(11)
);

CREATE TABLE IF NOT EXISTS `vsecciones` (
    `idSeccion` int(11)
    ,`nombreSeccion` varchar(30)
    ,`dia` int(11)
    ,`horaInicio` int(11)
    ,`horaFin` int(11)
    ,`capacidad` int(11)
    ,`idInstructor` int(11)
    ,`idClase` int(11)
    ,`nombreClase` varchar(30)
    ,`descripcion` text
    ,`precio` double
    ,`idSalon` int(11)
    ,`nombreSalon` varchar(30)
);

CREATE TABLE IF NOT EXISTS `vsocios` (
    `idSocio` int(11)
    ,`peso` double
    ,`estatura` double
    ,`idMembresia` int(11)
    ,`fechaIngreso` date
    ,`status` char(1)
    ,`idPersona` int(11)
    ,`cedula` varchar(11)
    ,`nombre` varchar(30)
    ,`apellido` varchar(30)
    ,`sexo` char(1)
    ,`fechaNacimiento` date
    ,`idDireccion` int(11)
);

DROP TABLE IF EXISTS `vadministrativos`;

CREATE VIEW `vadministrativos` AS select `personas`.`idPersona` AS `idPersona`,`personas`.`cedula` AS `cedula`,`personas`.`nombre` AS `nombre`,`personas`.`apellido` AS `apellido`,`personas`.`sexo` AS `sexo`,`personas`.`fechaNacimiento` AS `fechaNacimiento`,`personas`.`idDireccion` AS `idDireccion`,`persadministrativo`.`idEmpleado` AS `idEmpleado`,`persadministrativo`.`userName` AS `userName`,`persadministrativo`.`password` AS `password`,`empleados`.`puesto` AS `puesto`,`empleados`.`salario` AS `salario`,`empleados`.`fechaIngreso` AS `fechaIngreso` from ((`empleados` join `personas` on((`empleados`.`idPersona` = `personas`.`idPersona`))) join `persadministrativo` on((`empleados`.`idEmpleado` = `persadministrativo`.`idEmpleado`)));
DROP TABLE IF EXISTS `vempleados`;

CREATE VIEW `vempleados` AS select `empleados`.`idEmpleado` AS `idEmpleado`,`empleados`.`puesto` AS `puesto`,`empleados`.`salario` AS `salario`,`empleados`.`fechaIngreso` AS `fechaIngreso`,`personas`.`idPersona` AS `idPersona`,`personas`.`cedula` AS `cedula`,`personas`.`nombre` AS `nombre`,`personas`.`apellido` AS `apellido`,`personas`.`sexo` AS `sexo`,`personas`.`fechaNacimiento` AS `fechaNacimiento`,`personas`.`idDireccion` AS `idDireccion` from (`personas` join `empleados` on((`personas`.`idPersona` = `empleados`.`idPersona`)));
DROP TABLE IF EXISTS `vinstructores`;

CREATE VIEW `vinstructores` AS select `personas`.`idPersona` AS `idPersona`,`personas`.`cedula` AS `cedula`,`personas`.`nombre` AS `nombre`,`personas`.`apellido` AS `apellido`,`personas`.`sexo` AS `sexo`,`personas`.`fechaNacimiento` AS `fechaNacimiento`,`personas`.`idDireccion` AS `idDireccion`,`instructores`.`idInstructor` AS `idInstructor`,`instructores`.`especialidad` AS `especialidad`,`instructores`.`idEmpleado` AS `idEmpleado`,`empleados`.`puesto` AS `puesto`,`empleados`.`salario` AS `salario`,`empleados`.`fechaIngreso` AS `fechaIngreso` from ((`empleados` join `personas` on((`empleados`.`idPersona` = `personas`.`idPersona`))) join `instructores` on((`empleados`.`idEmpleado` = `instructores`.`idEmpleado`)));
DROP TABLE IF EXISTS `vseccion_socios`;

CREATE VIEW `vseccion_socios` AS select `socios`.`idSocio` AS `idSocio`,`socios`.`fechaIngreso` AS `fechaIngreso`,`socios`.`peso` AS `peso`,`socios`.`estatura` AS `estatura`,`socios`.`idMembresia` AS `idMembresia`,`socios`.`idPersona` AS `idPersona` from ((`socios` join `secciones_socio` on((`socios`.`idSocio` = `secciones_socio`.`idSocio`))) join `secciones` on((`secciones_socio`.`idSeccion` = `secciones`.`idSeccion`)));
DROP TABLE IF EXISTS `vsecciones`;

CREATE VIEW `vsecciones` AS select `secciones`.`idSeccion` AS `idSeccion`,`secciones`.`nombre` AS `nombreSeccion`,`secciones`.`dia` AS `dia`,`secciones`.`horaInicio` AS `horaInicio`,`secciones`.`horaFin` AS `horaFin`,`secciones`.`capacidad` AS `capacidad`,`secciones`.`idInstructor` AS `idInstructor`,`clases`.`idClase` AS `idClase`,`clases`.`nombre` AS `nombreClase`,`clases`.`descripcion` AS `descripcion`,`clases`.`precio` AS `precio`,`salones`.`idSalon` AS `idSalon`,`salones`.`nombre` AS `nombreSalon` from ((`secciones` join `clases` on((`secciones`.`idClase` = `clases`.`idClase`))) join `salones` on((`secciones`.`idSalon` = `salones`.`idSalon`)));
DROP TABLE IF EXISTS `vsocios`;

CREATE VIEW `vsocios` AS select `socios`.`idSocio` AS `idSocio`,`socios`.`peso` AS `peso`,`socios`.`estatura` AS `estatura`,`socios`.`idMembresia` AS `idMembresia`,`socios`.`fechaIngreso` AS `fechaIngreso`,`socios`.`status` AS `status`,`personas`.`idPersona` AS `idPersona`,`personas`.`cedula` AS `cedula`,`personas`.`nombre` AS `nombre`,`personas`.`apellido` AS `apellido`,`personas`.`sexo` AS `sexo`,`personas`.`fechaNacimiento` AS `fechaNacimiento`,`personas`.`idDireccion` AS `idDireccion` from (`socios` join `personas` on((`socios`.`idPersona` = `personas`.`idPersona`)));


ALTER TABLE `detallefactura`
  ADD CONSTRAINT `fk_DetalleFactura_facturas1` FOREIGN KEY (`idFactura`) REFERENCES `facturas` (`idFactura`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `empleados`
  ADD CONSTRAINT `fk_empleados_personas1` FOREIGN KEY (`idPersona`) REFERENCES `personas` (`idPersona`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `facturas`
  ADD CONSTRAINT `fk_Facturas_PersAdm` FOREIGN KEY (`idEmpleado`) REFERENCES `persadministrativo` (`idEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_facturas_socios1` FOREIGN KEY (`idSocio`) REFERENCES `socios` (`idSocio`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `instructores`
  ADD CONSTRAINT `fk_Instructores_Empleados1` FOREIGN KEY (`idEmpleado`) REFERENCES `empleados` (`idEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `pagos`
  ADD CONSTRAINT `fk_pagos_Facturas1` FOREIGN KEY (`idFactura`) REFERENCES `facturas` (`idFactura`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `persadministrativo`
  ADD CONSTRAINT `fk_Credenciales_Empleados1` FOREIGN KEY (`idEmpleado`) REFERENCES `empleados` (`idEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `personas`
  ADD CONSTRAINT `fk_Personas_Direcciones1` FOREIGN KEY (`idDireccion`) REFERENCES `direcciones` (`idDireccion`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `secciones`
  ADD CONSTRAINT `fk_Secciones_Clases1` FOREIGN KEY (`idClase`) REFERENCES `clases` (`idClase`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Secciones_Instructores1` FOREIGN KEY (`idInstructor`) REFERENCES `instructores` (`idInstructor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Secciones_Salones1` FOREIGN KEY (`idSalon`) REFERENCES `salones` (`idSalon`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `secciones_socio`
  ADD CONSTRAINT `fk_Secciones_Socio_Secciones1` FOREIGN KEY (`idSeccion`) REFERENCES `secciones` (`idSeccion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Secciones_Socio_Socios1` FOREIGN KEY (`idSocio`) REFERENCES `socios` (`idSocio`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `socios`
  ADD CONSTRAINT `fk_Socios_Membresias1` FOREIGN KEY (`idMembresia`) REFERENCES `membresias` (`idMembresia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_socios_personas1` FOREIGN KEY (`idPersona`) REFERENCES `personas` (`idPersona`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `telefonos`
  ADD CONSTRAINT `fk_telefonos_personas1` FOREIGN KEY (`idPersona`) REFERENCES `personas` (`idPersona`) ON DELETE NO ACTION ON UPDATE NO ACTION;

DELIMITER $$
CREATE  EVENT `updateFacturas` ON SCHEDULE EVERY 1 DAY STARTS '2012-05-05 20:42:52' ON COMPLETION NOT PRESERVE ENABLE DO call usp_generarFacturas;
$$

DELIMITER ;

