package com.jussystem.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.jussystem.model.Usuario;
import com.jussystem.repository.Usuarios;
import com.jussystem.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Usuario.class)
public class UsuarioConverter implements Converter{

	private Usuarios usuarios;
	
	public UsuarioConverter() {
		this.usuarios = CDIServiceLocator.getBean(Usuarios.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Usuario retorno = null;
		
		/*if(value != null) {
			retorno = this.usuarios.porId(new Long(value));	
			
		}*/
		
		if (value != null) {
			Long id = new Long(value);
			retorno = usuarios.porId(id);
		}
		return retorno;
	}

	//@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			Usuario usuario = (Usuario) value;
			return usuario.getId() == null ? null : usuario.getId().toString();
		}
		return "";
	}

}
