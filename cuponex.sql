-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-12-2022 a las 21:08:32
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 8.0.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `cuponex`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

CREATE TABLE `administrador` (
  `idAdministrador` int(10) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellidoPaterno` varchar(255) NOT NULL,
  `apellidoMaterno` varchar(255) NOT NULL,
  `correo` varchar(255) NOT NULL,
  `contrasena` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `administrador`
--

INSERT INTO `administrador` (`idAdministrador`, `nombre`, `apellidoPaterno`, `apellidoMaterno`, `correo`, `contrasena`) VALUES
(1, 'Bryan Usías', 'Catalino', 'Piquet', 'bryan@gmail.com', '12345'),
(2, 'Horus Alejandro', 'Hernández', 'Cardenas', 'cardenas@gmail.com', '54321');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `catalogo`
--

CREATE TABLE `catalogo` (
  `IdCatalogo` int(255) NOT NULL,
  `IdCategoria` int(255) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `catalogo`
--

INSERT INTO `catalogo` (`IdCatalogo`, `IdCategoria`, `nombre`) VALUES
(101, 1, 'Activo'),
(102, 1, 'Inactivo'),
(201, 2, 'Descuento'),
(202, 2, 'Costo Rebajado'),
(301, 3, 'Aerolineas'),
(302, 3, 'Cafe'),
(303, 3, 'Farmacias'),
(304, 3, 'Deportes'),
(305, 3, 'Cine');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresas`
--

CREATE TABLE `empresas` (
  `idEmpresa` int(10) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `nombreComercial` varchar(255) NOT NULL,
  `nombreRepresentante` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `calle` varchar(255) NOT NULL,
  `numero` varchar(255) NOT NULL,
  `codigoPostal` varchar(255) NOT NULL,
  `ciudad` varchar(255) NOT NULL,
  `telefono` varchar(255) NOT NULL,
  `paginaWeb` varchar(255) NOT NULL,
  `RFC` varchar(255) NOT NULL,
  `idEstatus` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `empresas`
--

INSERT INTO `empresas` (`idEmpresa`, `nombre`, `nombreComercial`, `nombreRepresentante`, `email`, `calle`, `numero`, `codigoPostal`, `ciudad`, `telefono`, `paginaWeb`, `RFC`, `idEstatus`) VALUES
(1, 'Casa Blanca', 'Tres Leches', 'Bryan Usías Catalino Piquet', 'leches@gmail.com', 'Principal', 'S/N', '94100', 'Alto Lucero', '2288765680', 'tres_leches.com', '1a2b3c', 101),
(3, 'Comex', 'Comex', 'Rafael Rojao', 'comex@gmail.com', 'avenidad americas', '100', '91170', 'Xalapa', '12345677890', 'comex_xalapa.com', '12345abc', 101);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `promociones`
--

CREATE TABLE `promociones` (
  `idPromocion` int(10) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `foto` blob DEFAULT NULL,
  `descripcion` varchar(255) NOT NULL,
  `fechaInicio` date DEFAULT NULL,
  `fechaFinal` date DEFAULT NULL,
  `restriccion` varchar(255) NOT NULL,
  `idTipoPromocion` int(10) NOT NULL,
  `porcentajeDescuento` varchar(255) NOT NULL,
  `costoPromocion` varchar(255) NOT NULL,
  `idCategoria` int(10) NOT NULL,
  `idEstatus` int(10) NOT NULL,
  `idEmpresa` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `promociones`
--

INSERT INTO `promociones` (`idPromocion`, `nombre`, `foto`, `descripcion`, `fechaInicio`, `fechaFinal`, `restriccion`, `idTipoPromocion`, `porcentajeDescuento`, `costoPromocion`, `idCategoria`, `idEstatus`, `idEmpresa`) VALUES
(1, 'tres leches por mil', '', 'leches', '2021-05-10', '2022-12-31', 'solo para casa blanca', 202, '20%', '100', 302, 101, 1),
(4, 'Fin de Año', NULL, 'Aprovecha todo antes de que se acabe el año', '2022-10-01', '2022-12-31', 'aprovecha lo maximo', 201, '30%', '300', 303, 101, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `promociones_sucursales`
--

CREATE TABLE `promociones_sucursales` (
  `id` int(10) NOT NULL,
  `idPromocion` int(10) NOT NULL,
  `idSucursal` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sucursales`
--

CREATE TABLE `sucursales` (
  `idSucursal` int(10) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `calle` varchar(255) NOT NULL,
  `numero` varchar(255) NOT NULL,
  `codigoPostal` varchar(255) NOT NULL,
  `colonia` varchar(255) NOT NULL,
  `ciudad` varchar(255) NOT NULL,
  `telefono` varchar(255) NOT NULL,
  `latitud` varchar(255) NOT NULL,
  `longitud` varchar(255) NOT NULL,
  `nombreEncargado` varchar(255) NOT NULL,
  `idEmpresa` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `sucursales`
--

INSERT INTO `sucursales` (`idSucursal`, `nombre`, `calle`, `numero`, `codigoPostal`, `colonia`, `ciudad`, `telefono`, `latitud`, `longitud`, `nombreEncargado`, `idEmpresa`) VALUES
(1, 'colas', 'Avenidad Xalapa', '2 de marzo', '91560', 'Enriquez', 'Xalapa', '1234567890', '90', '50', 'Iván Garcia', 1),
(3, 'comex Mini', 'Lazaro Cardenas', '190', '91300', 'michifus', 'Coatepec', '0987654321', '90', '80', 'Alessandra Reyes ;)', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idUsuario` int(10) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellidoPaterno` varchar(255) NOT NULL,
  `apellidoMaterno` varchar(255) NOT NULL,
  `telefono` varchar(255) NOT NULL,
  `correo` varchar(255) NOT NULL,
  `calle` varchar(255) NOT NULL,
  `numero` varchar(255) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `contrasena` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idUsuario`, `nombre`, `apellidoPaterno`, `apellidoMaterno`, `telefono`, `correo`, `calle`, `numero`, `fechaNacimiento`, `contrasena`) VALUES
(1, 'Pablo', 'Lucas', 'Negrete', '1234567890', 'lucas@gmail.com', '1 noviembre', '5', '2000-07-10', '12345'),
(2, 'Jair', 'Vasquez', 'Rendón', '0987654321', 'redondo@gmail.com', '16 abril', '3', '1999-11-10', '12345'),
(3, 'Rafael Alberto', 'Apodaca', 'Rojano', '6543210987', 'apodaca@gmail.com', 'Principal', '5 ', '2000-04-23', '12345');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD PRIMARY KEY (`idAdministrador`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- Indices de la tabla `empresas`
--
ALTER TABLE `empresas`
  ADD PRIMARY KEY (`idEmpresa`),
  ADD KEY `idEstatus` (`idEstatus`);

--
-- Indices de la tabla `promociones`
--
ALTER TABLE `promociones`
  ADD PRIMARY KEY (`idPromocion`),
  ADD KEY `idTipoPromocion` (`idTipoPromocion`),
  ADD KEY `idCategoria` (`idCategoria`),
  ADD KEY `idEstatus` (`idEstatus`),
  ADD KEY `idEmpresa` (`idEmpresa`);

--
-- Indices de la tabla `promociones_sucursales`
--
ALTER TABLE `promociones_sucursales`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idPromocion` (`idPromocion`),
  ADD KEY `idSucursal` (`idSucursal`);

--
-- Indices de la tabla `sucursales`
--
ALTER TABLE `sucursales`
  ADD PRIMARY KEY (`idSucursal`),
  ADD KEY `idEmpresa` (`idEmpresa`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idUsuario`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `administrador`
--
ALTER TABLE `administrador`
  MODIFY `idAdministrador` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `empresas`
--
ALTER TABLE `empresas`
  MODIFY `idEmpresa` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `promociones`
--
ALTER TABLE `promociones`
  MODIFY `idPromocion` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `promociones_sucursales`
--
ALTER TABLE `promociones_sucursales`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `sucursales`
--
ALTER TABLE `sucursales`
  MODIFY `idSucursal` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsuario` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
