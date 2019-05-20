----================== INSERIR VALORES NAS TABELAS ===================-----------------
use av2_campeonato

insert into paises values 
('Brasil', 'BRA'),
('CUBA', 'CUB'),
('JAPÃO', 'JPN'),
('Australia', 'AUS'),
('ALEMANHA', 'GER'),
('GRECIA', 'GRE'),
('NIGERIA', 'NGR'),
('PERU', 'PER'),
('Wakanda', 'WKD'),
('Russia','RUS')

exec p_insere_atleta 'Atleta1','BRA','FEM'
exec p_insere_atleta 'Atleta2','CUB','MASC'
exec p_insere_atleta 'Atleta3','JPN','FEM'
exec p_insere_atleta 'Atleta4','AUS','MASC'
exec p_insere_atleta 'Atleta5','GER','FEM'
exec p_insere_atleta 'Atleta6','GRE','MASC'
exec p_insere_atleta 'Atleta7','NGR','FEM'
exec p_insere_atleta 'Atleta8','PER','MASC'
exec p_insere_atleta 'Atleta9','WKD','FEM'
exec p_insere_atleta 'Atleta10','RUS','MASC'

exec p_insere_prova 'corrida 100m rasos/m', 'masc'
exec p_insere_prova 'corrida 100m rasos/f', 'fem'
exec p_insere_prova 'arremeso de peso/f', 'fem'
exec p_insere_prova 'arremeso de peso/m', 'masc'
exec p_insere_prova 'corrida 100m barreiras/m', 'masc'
exec p_insere_prova 'corrida 100m barreiras/f', 'fem'
exec p_insere_prova 'salto com vara/f', 'fem'
exec p_insere_prova 'salto com vara/m', 'masc'

--=================================================================================================
--@id_at int, @id_prova int, @num_distancia int,@resultado varchar(20), @tabela varchar(20)
---------------------- tempo --------------------------


exec p_insere_fase 1, 2, null, '00:00:00:100', 'inicial'
exec p_insere_fase 2, 1, null, '00:00:45', 'inicial'
exec p_insere_fase 3, 2, null, '00:00:35', 'inicial'
exec p_insere_fase 4, 1, null, '00:00:25', 'inicial'
exec p_insere_fase 5, 2, null, '00:00:15', 'inicial'
exec p_insere_fase 6, 5, null, '00:00:05', 'inicial'
exec p_insere_fase 7, 6, null, '00:01:55', 'inicial'
exec p_insere_fase 8, 5, null, '00:01:00', 'inicial'
exec p_insere_fase 9, 6, null, '00:01:25', 'inicial'
exec p_insere_fase 10, 5, null, '00:01:35', 'inicial'



-------------------- distancia ----------------------------

exec p_insere_fase 10, 4, 1, '10', 'inicial'
exec p_insere_fase 10, 4, 2, '150', 'inicial'
exec p_insere_fase 10, 4, 3, '250', 'inicial'
exec p_insere_fase 10, 4, 4, '325', 'inicial'
exec p_insere_fase 10, 4, 5, '415', 'inicial'
exec p_insere_fase 10, 4, 6, '835', 'inicial'






select *  from fase_inicial
select *  from fase_final
select * from resultado


select * from f_resultado('inicial') where id_prova = 4 order by marca

---- dados que o combobosta vai usar pra inserir as fases finais
---- ============ select tempo inicial ===========---------

select top 8 id_atleta, nome_atleta, coi, tempo from fase_inicial
order by tempo asc



---- ============ select tempo final ===========---------

select top 3 id_atleta, nome_atleta, coi, tempo from fase_final
order by tempo asc 





---- dados que o combobosta vai usar pra inserir as fases finais
---- ============ select DISTANCIA inicial ===========---------

select top 8 fi.id_atleta, fi.nome_atleta, fi.coi, res.distancia
from fase_inicial fi inner join resultado res
on fi.id_atleta = res.id_atleta and fi.id_prova = res.id_prova
order by res.distancia desc



---- ============ select Distancia final ===========---------

select top 3 fi.id_atleta, fi.nome_atleta, fi.coi, res.distancia
from fase_final fi inner join resultado res
on fi.id_atleta = res.id_atleta and fi.id_prova = res.id_prova
order by res.distancia desc

------------------------------------------------------------------

select *  from resultado
where id_prova=4 and fase='inicial'

--------------------------------------------------------------------

insert into record_mundial values
(1, 'Atleta1', 'masc' , 'BRA', 1, '00:01:00', null, GETDATE()),
(2, 'Atleta1', 'masc' , 'BRA', 2, null, '999', GETDATE()),
(3, 'Atleta1', 'masc' , 'BRA', 3, '00:04:00', null, GETDATE()),
(4, 'Atleta1', 'masc' , 'BRA', 4, null, '909', GETDATE())

select * from record_mundial


insert into record_evento values
(1, 'Atleta1', 'masc' , 'BRA', 1, '00:01:00', null, GETDATE()),
(2, 'Atleta2', 'masc' , 'BRA', 2, null, '999', GETDATE()),
(3, 'Atleta3', 'masc' , 'BRA', 3, '00:04:00', null, GETDATE()),
(4, 'Atleta4', 'masc' , 'BRA', 4, null, '909', GETDATE())

select * from record_evento


--SELECT FLOOR(RAND()*(10-1)+1)