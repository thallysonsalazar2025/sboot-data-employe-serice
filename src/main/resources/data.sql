-- =========================
-- EMPRESAS
-- =========================
INSERT INTO company (id, name, registration_number) VALUES
(1, 'Prefeitura de Araçoiaba da Serra - SP', '46634044000174'),
(2, 'Prefeitura de Capela do Alto - SP', '46634077000114'),
(3, 'NKY-Solucoes - SC', '12345678000199');

-- =========================
-- FUNCIONÁRIOS
-- =========================

-- Funcionários - Araçoiaba da Serra
INSERT INTO employee (id, name, email, company_id, registration_number) VALUES
(1, 'João Silva', 'joao.silva@aracoiaba.sp.gov.br', 1, 'REG-001'),
(2, 'Maria Souza', 'maria.souza@aracoiaba.sp.gov.br', 1, NULL),
(3, 'Carlos Pereira', 'carlos.pereira@aracoiaba.sp.gov.br', 1, NULL);

-- Funcionários - Capela do Alto
INSERT INTO employee (id, name, email, company_id) VALUES
(4, 'Ana Oliveira', 'ana.oliveira@capela.sp.gov.br', 2),
(5, 'Bruno Santos', 'bruno.santos@capela.sp.gov.br', 2),
(6, 'Fernanda Lima', 'fernanda.lima@capela.sp.gov.br', 2);

-- Funcionários - NKY Soluções
INSERT INTO employee (id, name, email, company_id) VALUES
(7, 'Lucas Martins', 'lucas.martins@nky.com.br', 3),
(8, 'Patricia Alves', 'patricia.alves@nky.com.br', 3),
(9, 'Rafael Costa', 'rafael.costa@nky.com.br', 3);