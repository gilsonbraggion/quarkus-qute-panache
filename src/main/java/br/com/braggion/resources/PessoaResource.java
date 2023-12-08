package br.com.braggion.resources;

import java.net.URI;
import java.util.List;

import br.com.braggion.model.Pessoa;
import br.com.braggion.tipos.Sexo;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path(value = "/pessoa")
public class PessoaResource {

	@CheckedTemplate(requireTypeSafeExpressions = false)
	private static class Templates {
		public static native TemplateInstance listagem(List<Pessoa> listagem);

		public static native TemplateInstance cadastro(Pessoa pessoa);
	}

	@GET
	@Path(value = "/listagem")
	@Produces(MediaType.TEXT_HTML)
	@Transactional
	public TemplateInstance listagem() {

		return Templates.listagem(Pessoa.listAll());
	}

	@GET
	@Path(value = "/novo")
	@Produces(MediaType.TEXT_HTML)
	@Transactional
	public TemplateInstance cadastro() {
		Pessoa pessoa = new Pessoa();
		return Templates.cadastro(pessoa);
	}

	@GET
	@Path(value = "/editar/{id}")
	@Produces(MediaType.TEXT_HTML)
	@Transactional
	public TemplateInstance cadastro(String id) {
		return Templates.cadastro(Pessoa.findById(id));
	}

	@POST
	@Path(value = "/salvar")
	@Transactional
	public Response salvar(@FormParam("id") String id, @FormParam("nome") String nome, @FormParam("idade") Integer idade, @FormParam("sexo") Sexo sexo) {

		Pessoa pessoa = new Pessoa();

		if (id != null && !id.isEmpty()) {
			pessoa = Pessoa.findById(id);
		}

		pessoa.nome = nome;
		pessoa.idade = idade;
		pessoa.sexo = sexo;

		pessoa.persist();

		return Response.status(301).location(URI.create("/pessoa/listagem")).build();
	}

}
