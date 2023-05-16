-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 16-05-2023 a las 10:35:22
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyecto_hospital`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Cama`
--

CREATE TABLE `Cama` (
  `numero_cama` int(11) NOT NULL,
  `numero_habitacion` int(100) NOT NULL,
  `disponible` tinyint(1) DEFAULT NULL,
  `paciente_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `Cama`
--

INSERT INTO `Cama` (`numero_cama`, `numero_habitacion`, `disponible`, `paciente_id`) VALUES
(1, 0, 1, NULL),
(2, 1, 1, NULL),
(3, 1, 1, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Ingreso`
--

CREATE TABLE `Ingreso` (
  `numero_ingreso` int(11) NOT NULL,
  `fecha_ingreso` date NOT NULL,
  `fecha_alta` date DEFAULT NULL,
  `paciente_id` int(11) NOT NULL,
  `medico_id` int(11) NOT NULL,
  `cama_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `Ingreso`
--

INSERT INTO `Ingreso` (`numero_ingreso`, `fecha_ingreso`, `fecha_alta`, `paciente_id`, `medico_id`, `cama_id`) VALUES
(1, '2023-05-01', '2023-02-07', 1, 1, 1),
(2, '2023-03-02', NULL, 2, 1, 2),
(3, '2023-04-02', NULL, 3, 2, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Medico`
--

CREATE TABLE `Medico` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `especialidad` enum('Alergología','Anestesiología_Reanimación','AparatoDigestivo','Cardiología','EndocrinologíaNutrición','Geriatría','HematologíaHemoterapia','MedicinaEducaciónFísicaDeporte','MedicinaEspacial','MedicinaIntensiva','MedicinaInterna','MedicinaLegalForense','MedicinaPreventivaSaludPublica','MedicinaTrabajo','Nefrología','Neumología','Neurología','NeurofisiologíaClínica','OncologíaMédica','OncologíaRadioterápica','Pediatría','Psiquiatría','Rehabilitación','Reumatología','MedicinaFamiliarComunitaria','CirugíaCardiovascular','CirugíaGeneralAparatoDigestivo','CirugíaOralMaxilofacial','CirugíaOrtopédicaTraumatología','CirugíaPediátrica','CirugíaPlásticaEstéticaReparadora','CirugíaTorácica','Neurocirugía','AngiologíaCirugíaVascular','DermatologíaMédicoquirúrgicaVenereología','ObstetriciaGinecología','Oftalmología','Otorrinolaringología','Urología','AnálisisClínicos','AnatomíaPatológica','BioquímicaClínica','FarmacologíaClínica','Inmunología','MedicinaNuclear','MicrobiologíaParasitología','Radiodiagnóstico') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `Medico`
--

INSERT INTO `Medico` (`codigo`, `nombre`, `apellido`, `telefono`, `especialidad`) VALUES
(1, 'Antonio', 'Blanco', '657656778', 'MedicinaFamiliarComunitaria'),
(2, 'Carlos', 'Garcia', '675453454', 'Alergología'),
(3, 'Lucia', 'Pineda', '654456567', 'Pediatría'),
(4, 'Juan', 'Perez', '123456789', 'Alergología'),
(5, 'Pablo', 'Perez', '678898787', 'Cardiología');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Paciente`
--

CREATE TABLE `Paciente` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(70) NOT NULL,
  `edad` int(11) NOT NULL,
  `genero` varchar(10) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `poblacion` varchar(70) NOT NULL,
  `provincia` varchar(70) NOT NULL,
  `codigoPostal` int(5) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `estado` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `Paciente`
--

INSERT INTO `Paciente` (`id`, `nombre`, `apellidos`, `edad`, `genero`, `direccion`, `poblacion`, `provincia`, `codigoPostal`, `telefono`, `estado`) VALUES
(1, 'Manuel', 'Gasca Gallego', 50, 'masculino', 'Calle Carlos Soler, 3', 'sevilla', 'sevilla', 11567, '654456543', 'ingresado'),
(2, 'Jose Luis', '', 21, 'Masculino', 'Calle Consolacion 2', '', '', 0, '654345676', ''),
(3, 'Irene', '', 3, 'Femenino', 'Av. Matea 8', '', '', 0, '657453454', 'Sano'),
(8, 'sdf', 'sdf', 34, 'dsf', 'dsf', 'df', 'dsf', 214, '324', 'sd');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Cama`
--
ALTER TABLE `Cama`
  ADD PRIMARY KEY (`numero_cama`),
  ADD KEY `paciente_id` (`paciente_id`);

--
-- Indices de la tabla `Ingreso`
--
ALTER TABLE `Ingreso`
  ADD PRIMARY KEY (`numero_ingreso`),
  ADD KEY `paciente_id` (`paciente_id`),
  ADD KEY `medico_id` (`medico_id`),
  ADD KEY `cama_id` (`cama_id`);

--
-- Indices de la tabla `Medico`
--
ALTER TABLE `Medico`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `Paciente`
--
ALTER TABLE `Paciente`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Ingreso`
--
ALTER TABLE `Ingreso`
  MODIFY `numero_ingreso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `Paciente`
--
ALTER TABLE `Paciente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Cama`
--
ALTER TABLE `Cama`
  ADD CONSTRAINT `cama_ibfk_1` FOREIGN KEY (`paciente_id`) REFERENCES `Paciente` (`id`);

--
-- Filtros para la tabla `Ingreso`
--
ALTER TABLE `Ingreso`
  ADD CONSTRAINT `ingreso_ibfk_1` FOREIGN KEY (`paciente_id`) REFERENCES `Paciente` (`id`),
  ADD CONSTRAINT `ingreso_ibfk_2` FOREIGN KEY (`medico_id`) REFERENCES `Medico` (`codigo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
