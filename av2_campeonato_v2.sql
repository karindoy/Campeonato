create database av2_campeonato
go
use av2_campeonato

create table paises (
nome varchar(100),
coi varchar(3)

primary key (coi) 
)

create table atleta (
id int identity,
nome varchar(100),
coi varchar(3),
sexo varchar(10)

primary key (id),
foreign key (coi) references paises(coi)
)

create table prova (
id int identity,
nome varchar(100),
sexo varchar(10)

primary key (id),
)

create table fase_inicial(
id_atleta int,
id_prova int,
nome_atleta varchar(100),
coi varchar(3),
tempo time,
distancia1 decimal (7,3),
distancia2 decimal (7,3),
distancia3 decimal (7,3),
distancia4 decimal (7,3),
distancia5 decimal (7,3),
distancia6 decimal (7,3)  

--foreign key (id_atleta) references atleta(id),
foreign key (id_prova) references prova(id)
)
-------

create table fase_final(
id_atleta int,
id_prova int,
nome_atleta varchar(100),
coi varchar(3),
tempo time,
distancia1 decimal (7,3),
distancia2 decimal (7,3),
distancia3 decimal (7,3),
distancia4 decimal (7,3),
distancia5 decimal (7,3),
distancia6 decimal (7,3)   

foreign key (id_atleta) references atleta(id),
foreign key (id_prova) references prova(id)
)

create table resultado(
id_atleta int,
nome_atleta varchar(100),
coi varchar(3),
id_prova int,
tempo time,
distancia decimal (7,3),
fase varchar(10)

foreign key (id_atleta) references atleta(id),
foreign key (id_prova) references prova(id)
)

create table record_mundial(
id_atleta int,
nome_atleta varchar(100),
sexo_atleta varchar(10),
coi varchar(3),
id_prova int,
tempo time,
distancia decimal (7,3),
data datetime

foreign key (id_atleta) references atleta(id),
foreign key (id_prova) references prova(id)
)

create table record_evento(
id_atleta int,
nome_atleta varchar(100),
sexo_atleta varchar(10),
coi varchar(3),
id_prova int,
tempo time,
distancia decimal (7,3),
data datetime

foreign key (id_atleta) references atleta(id),
foreign key (id_prova) references prova(id)
)

--- ======================================================================================================================== ---

--------------------------- proc. para INSERIR ATLETAS na tabela 
go
create procedure p_insere_atleta(@nome varchar(100),@coi varchar(3),@sexo varchar(10))
as
	insert into atleta(nome,coi,sexo) values (@nome, @coi, @sexo)

--------------------------- fim procedure p_insere_atleta



--------------------------- proc. para INSERIR PROVAS na tabela 
go
create procedure p_insere_prova(@nome varchar(100),@sexo varchar(10))
as
	insert into prova(nome,sexo) values (@nome, @sexo)

--------------------------- fim procedure p_insere_prova

--------------------------- proc. para INSERIR PAISES na tabela 
go
create procedure p_insere_paises(@nome varchar(100),@coi varchar(3))
as
	insert into paises(nome,coi) values (@nome, @coi)

--------------------------- fim procedure p_insere_paises

--- ======================================================================================================================== ---


go

--------------------------- proc. para inserir resultados na tabela FASE_INICIAL/FASE_FINAL
--drop procedure p_insere_fase
create procedure p_insere_fase(@id_at int, @id_prova int, @num_distancia int,@resultado varchar(20), @tabela varchar(20))
as
	declare 
			@query varchar(max),
			@tempo time,
			@distancia decimal(7,3),
			@nome_at varchar(100),
			@coi_at varchar(3)
			
		set @nome_at = (select nome from atleta where id = @id_at)
		set @coi_at = (select coi from atleta where id = @id_at)
		
		begin try
			set @tempo = (select convert (time, @resultado))
			set @query = 'insert into fase_'+@tabela + ' (id_atleta, id_prova, nome_atleta, coi, tempo) values ('
						+CAST(@id_at as varchar(10))+','
						+CAST(@id_prova as varchar(10))+',
						'''+@nome_at+''',
						'''+@coi_at+''',
						'''+@resultado+''')'
						
			exec(@query) 	
			exec passarResultadoTempo @id_at, @id_prova ,@tabela, @tempo					
		end try

		begin catch
			if(@num_distancia=1)
			begin
				set @distancia = (select convert (decimal(7,3), @resultado))
				set @query = 'insert into fase_'+@tabela + ' (id_atleta, id_prova, nome_atleta, coi, distancia'+cast (@num_distancia as varchar(1))+') values ('
							+CAST(@id_at as varchar(10))+','
							+CAST(@id_prova as varchar(10))+',
							'''+@nome_at+''',
							'''+@coi_at+''',
							'''+@resultado+''')'
				exec(@query)			
				
			end
			else
			begin
				set @query = 'update fase_'+@tabela + ' set distancia'+cast (@num_distancia as varchar(1))
							+'='''+@resultado+''' where id_atleta='+cast(@id_at as varchar(10))+' 
							 and id_prova='+cast(@id_prova as varchar(10))
				exec(@query)
						
				if(@num_distancia=6)
				begin
					exec passarResultadoDistancia @id_at, @id_prova ,@tabela
				end 
			end
		end catch
		
		exec atualizaRecorde @id_at, @id_prova, @resultado


go
--------------------------- fim procedure p_insere_inicial
create procedure passarResultadoDistancia(@id_at int, @id_prova int, @tabela varchar(20))
as
	declare
		@maior_dist decimal(7,3)

		set @maior_dist = (select max(distancia) from dbo.maiorDistancia(@id_at , @id_prova))
		insert into resultado(id_atleta,id_prova, distancia, fase) values
		(@id_at, @id_prova, @maior_dist, @tabela)
go









create procedure passarResultadoTempo(@id_at int, @id_prova int, @tabela varchar(20), @tempo time)
as
		insert into resultado(id_atleta,id_prova, tempo, fase) values
		(@id_at, @id_prova, @tempo, @tabela)
		




go


create procedure atualizaRecorde(@id_at int, @id_prova int, @resultado varchar(20))
as	
	declare 
			@query varchar(max),
			@tempo time,
			@distancia decimal(7,3),
			@nome_at varchar(100),
			@coi_at varchar(3),
			@sexo varchar(10),
			@recordeT time,
			@recordeD decimal(7,3)
			
		set @nome_at = (select nome from atleta where id = @id_at)
		set @coi_at = (select coi from atleta where id = @id_at)
		set @sexo = (select sexo from atleta where id = @id_at)
		
		begin try
		
			set @tempo = (select convert (time, @resultado))
			set @recordeT = (select tempo from record_evento where @id_prova = id_prova)
			
			if(@tempo<@recordeT)
			begin
				update record_evento 
				set id_atleta = @id_at, nome_atleta = @nome_at, sexo_atleta = @sexo, coi = @coi_at, tempo = @tempo
				where @id_prova = id_prova
			end
			
			set @recordeT = (select tempo from record_mundial where @id_prova = id_prova)
			
			if(@tempo<@recordeT)
			begin
				update record_mundial
				set id_atleta = @id_at, nome_atleta = @nome_at, sexo_atleta = @sexo, coi = @coi_at, tempo = @tempo
				where @id_prova = id_prova
			end
			
		end try
		
		begin catch
			set @distancia = (select convert (decimal(7,3), @resultado))
			set @recordeD = (select distancia from record_evento where @id_prova = id_prova)
			
			if(@distancia>@recordeD)
			begin
				update record_mundial 
				set id_atleta = @id_at, nome_atleta = @nome_at, sexo_atleta = @sexo, coi = @coi_at, distancia = @distancia
				where @id_prova = id_prova
			end
			
			set @recordeD = (select distancia from record_mundial where @id_prova = id_prova)
			
			if(@distancia>@recordeD)
			begin
				update record_mundial
				set id_atleta = @id_at, nome_atleta = @nome_at, sexo_atleta = @sexo, coi = @coi_at, distancia = @distancia
				where @id_prova = id_prova
			end
		end catch





go
-- ===== FUNCAO MAIOR DISTANCIA ===========
create function maiorDistancia(@id_at int , @id_prova int )
returns @table table(
distancia Decimal(7,3)
)
as 
begin
 declare @d1 Decimal(7,3),
		 @d2 Decimal(7,3),
		 @d3 Decimal(7,3),
		 @d4 Decimal(7,3),
		 @d5 Decimal(7,3),
		 @d6 Decimal(7,3)
		 		
		select @d1=distancia1, @d2=distancia2, @d3=distancia3,
				 @d4=distancia4, @d5=distancia5, @d6=distancia6 
		from fase_inicial where id_atleta=@id_at and id_prova=@id_prova
			
		insert into @table values
		(@d1), (@d2), (@d3), (@d4), (@d5), (@d6)
return
end







---- ================= TRIGOS ====================------
go
CREATE TRIGGER t_manterResultado
on resultado 
for update, delete
as
begin
	rollback transaction
	raiserror('nao é possivel atualizar ou deletar',16,1)	
end

go
CREATE TRIGGER t_manterAtleta
on Atleta
for update, delete
as
begin
	rollback transaction
	raiserror('nao é possivel atualizar ou deletar',16,1)	
end

go
CREATE TRIGGER t_manterPaises
on paises
for update, delete
as
begin
	rollback transaction
	raiserror('nao é possivel atualizar ou deletar',16,1)	
end

delete resultado
select * from record_evento
select * from record_mundial


create function f_record(@record varchar(10))
returns @tabela table(
id_atleta int,
nome_atleta varchar(100),
sexo_atleta varchar(10),
coi varchar (3),
id_prova int,
marca varchar(50),
data date
)
as 
begin
declare @id_atleta int,
		@nome_atleta varchar(100),
		@sexo_atleta varchar(10),
		@coi varchar (3),
		@id_prova int,
		@marca varchar(50),
		@data date,
		@tempo time,
		@distancia decimal(7,3),
		@cont int,
		@n int

	set @cont =(select COUNT(id_atleta) from record_mundial) 
	set @n =1
	while (@n<=@cont)
	begin
		
		if(@record='mundial')
		begin
			select top (@n) @id_atleta=id_atleta, @nome_atleta=nome_atleta, @sexo_atleta=sexo_atleta, 
							@coi= coi, @id_prova=id_prova, @data=data
			from record_mundial
				
			select top (@n) @tempo=tempo, @distancia=distancia
			from record_mundial
		end
		else
			begin
			select top (@n) @id_atleta=id_atleta, @nome_atleta=nome_atleta, @sexo_atleta=sexo_atleta, 
							@coi= coi, @id_prova=id_prova, @data=data
			from record_evento
				
			select top (@n) @tempo=tempo, @distancia=distancia
			from record_evento
		end

		if( @tempo is not null)
		begin
			insert into @tabela values 
			(@id_atleta, @nome_atleta, @sexo_atleta, @coi, @id_prova, cast(@tempo as varchar(50)), @data) 
		end
		else
		begin
			insert into @tabela values 
			(@id_atleta, @nome_atleta, @sexo_atleta, @coi, @id_prova, cast(@distancia as varchar(100)), @data) 
		end
		
		set @n= @n+1
	end
return
end

select * from record_evento
select * from f_record('evento') where id_prova = 1 order by marca 
select * from f_record('mundial') where id_prova = 3 order by marca 

go
create function f_resultado(@fase varchar(10))
returns @tabela table(
id_atleta int,
nome_atleta varchar(100),
coi varchar (3),
id_prova int,
marca varchar(50)
)
as 
begin
declare @id_atleta int,
		@nome_atleta varchar(100),
		@coi varchar (3),
		@id_prova int,
		@marca varchar(50),
		@tempo time,
		@distancia decimal(7,3),
		@cont int,
		@n int

	set @cont =(select COUNT(id_atleta) from resultado where fase=@fase) 
	set @n =1
	while (@n<=@cont)
	begin
		
			select top(@n)@id_atleta=id_atleta, @nome_atleta=nome_atleta, 
							@coi= coi, @id_prova=id_prova
			from resultado where fase=@fase
				
			select top(@n) @tempo=tempo, @distancia=distancia
			from resultado where fase=@fase


		if( @tempo is not null)
		begin
			insert into @tabela values 
			(@id_atleta, @nome_atleta, @coi, @id_prova, cast(@tempo as varchar(50))) 
		end
		else
		begin
			insert into @tabela values 
			(@id_atleta, @nome_atleta, @coi, @id_prova, cast(@distancia as varchar(100))) 
		end
		
		set @n= @n+1
	end
return
end

select * from resultado   where id_prova = 5
select * from f_resultado('final') where id_prova = 5 order by marca 