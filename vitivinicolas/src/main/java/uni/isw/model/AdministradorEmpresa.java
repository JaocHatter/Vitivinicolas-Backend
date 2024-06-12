package uni.isw.model;

import jakarta.persistence.*;

@Table(name="administradorempresas")
public class AdministradorEmpresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long administradorId;

    @Column(name = "nombre_admin")
    private String nombreAdmin;

    @Column(name = "apellidos_admin")
    private String apellidosAdmin;
}
