-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 18-Maio-2023 às 02:22
-- Versão do servidor: 10.4.24-MariaDB
-- versão do PHP: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `loja`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `clientes`
--

CREATE TABLE `clientes` (
  `id` int(11) NOT NULL,
  `nome` varchar(80) NOT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  `rg` varchar(30) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `rua` varchar(100) DEFAULT NULL,
  `bairro` varchar(50) DEFAULT NULL,
  `cep` varchar(9) DEFAULT NULL,
  `num` varchar(5) DEFAULT NULL,
  `sexo` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `clientes`
--

INSERT INTO `clientes` (`id`, `nome`, `cpf`, `rg`, `telefone`, `rua`, `bairro`, `cep`, `num`, `sexo`) VALUES
(1, 'JOSE', '11111', '2222', '', '', '', '', '', ''),
(2, 'MARIA', '2222', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `fpag`
--

CREATE TABLE `fpag` (
  `id` int(11) NOT NULL,
  `descricao` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `fpag`
--

INSERT INTO `fpag` (`id`, `descricao`) VALUES
(1, 'DINHEIRO'),
(2, 'PIX');

-- --------------------------------------------------------

--
-- Estrutura da tabela `itemvenda`
--

CREATE TABLE `itemvenda` (
  `id` int(11) NOT NULL,
  `venda_id` int(11) NOT NULL,
  `valoruni` decimal(15,2) NOT NULL,
  `qtd` decimal(15,3) NOT NULL,
  `valortotal` decimal(15,2) NOT NULL,
  `produtos_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `itemvenda`
--

INSERT INTO `itemvenda` (`id`, `venda_id`, `valoruni`, `qtd`, `valortotal`, `produtos_id`) VALUES
(1, 10, '121.00', '1.000', '121.00', 3),
(2, 10, '121.00', '1.000', '121.00', 3),
(3, 10, '545.00', '1.000', '545.00', 6),
(4, 10, '545.00', '2.000', '1090.00', 6),
(5, 11, '545.00', '1.000', '545.00', 6);

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtos`
--

CREATE TABLE `produtos` (
  `id` int(11) NOT NULL,
  `descricao` varchar(150) NOT NULL,
  `valor` decimal(15,2) NOT NULL,
  `qtd` decimal(15,3) NOT NULL,
  `codbarra` varchar(32) DEFAULT NULL,
  `peso` decimal(15,3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `produtos`
--

INSERT INTO `produtos` (`id`, `descricao`, `valor`, `qtd`, `codbarra`, `peso`) VALUES
(3, 'MOUSE', '121.00', '12.000', '121', '21.000'),
(5, 'teclado', '1212.00', '1.000', '1231', '1.000'),
(6, 'PEN DRIVE', '545.00', '45.000', '545', '454.000');

-- --------------------------------------------------------

--
-- Estrutura da tabela `venda`
--

CREATE TABLE `venda` (
  `id` int(11) NOT NULL,
  `total` decimal(15,2) NOT NULL,
  `data` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `clientes_id` int(11) NOT NULL,
  `fpag_id` int(11) NOT NULL,
  `status` char(1) NOT NULL DEFAULT 'I'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `venda`
--

INSERT INTO `venda` (`id`, `total`, `data`, `clientes_id`, `fpag_id`, `status`) VALUES
(1, '100.00', '2023-04-25 03:00:00', 1, 1, 'I'),
(2, '200.00', '2023-04-25 23:18:48', 2, 2, 'I'),
(5, '0.00', '2023-05-17 23:27:10', 1, 1, 'I'),
(6, '0.00', '2023-05-17 23:38:46', 1, 1, 'I'),
(7, '0.00', '2023-05-17 23:39:25', 1, 1, 'I'),
(8, '0.00', '2023-05-17 23:49:51', 1, 1, 'I'),
(9, '0.00', '2023-05-17 23:54:35', 1, 1, 'C'),
(10, '0.00', '2023-05-18 00:20:40', 1, 1, 'C'),
(11, '0.00', '2023-05-18 00:20:43', 1, 1, 'I');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `fpag`
--
ALTER TABLE `fpag`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `itemvenda`
--
ALTER TABLE `itemvenda`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_itemvenda_venda` (`venda_id`),
  ADD KEY `fk_itemvenda_produto` (`produtos_id`);

--
-- Índices para tabela `produtos`
--
ALTER TABLE `produtos`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `venda`
--
ALTER TABLE `venda`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_venda_cliente` (`clientes_id`),
  ADD KEY `fk_venda_fpag` (`fpag_id`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `fpag`
--
ALTER TABLE `fpag`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `itemvenda`
--
ALTER TABLE `itemvenda`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `produtos`
--
ALTER TABLE `produtos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de tabela `venda`
--
ALTER TABLE `venda`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `itemvenda`
--
ALTER TABLE `itemvenda`
  ADD CONSTRAINT `fk_itemvenda_produto` FOREIGN KEY (`produtos_id`) REFERENCES `produtos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_itemvenda_venda` FOREIGN KEY (`venda_id`) REFERENCES `venda` (`id`);

--
-- Limitadores para a tabela `venda`
--
ALTER TABLE `venda`
  ADD CONSTRAINT `fk_venda_cliente` FOREIGN KEY (`clientes_id`) REFERENCES `clientes` (`id`),
  ADD CONSTRAINT `fk_venda_fpag` FOREIGN KEY (`fpag_id`) REFERENCES `fpag` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
