package br.com.caelum.tarefas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.caelum.tarefas.modelo.Tarefa;


@Repository
public class JdbcTarefaDao {

	private Connection connection;

	@Autowired
	public JdbcTarefaDao(DataSource ds) {
		try {
			this.connection = ds.getConnection();
			System.out.println("conectado");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public JdbcTarefaDao() {

	}

	public void adiciona(Tarefa tarefa) {
		String sqlInsert = "INSERT INTO tarefas(descricao) VALUES (?)";
		System.out.println(tarefa.getDescricao());
		try (PreparedStatement stm = this.connection.prepareStatement(sqlInsert);) {
			
			stm.setString(1, tarefa.getDescricao());
			/*stm.setBoolean(2, tarefa.isFinalizado());
			stm.setDate(3, new java.sql.Date(tarefa.getDataFinalizacao().getTime()));*/

			
			stm.execute();

/*			// recupera chave do objeto
			ResultSet rs = stm.getGeneratedKeys();
			Long idProduto = null;
			while (rs.next()) {
				idProduto = rs.getLong(1);
			}
			// coloca o id retornado no id do obj
			tarefa.setId(idProduto);*/
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.connection.rollback();
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
		}
	}

}
