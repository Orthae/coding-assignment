package orthae.com.github.taskservice;

import orthae.com.github.taskservice.application.model.TaskModel;
import orthae.com.github.taskservice.application.AuthenticatedUser;
import orthae.com.github.taskservice.application.Role;
import orthae.com.github.taskservice.application.model.TaskStatusModel;
import orthae.com.github.taskservice.application.model.UserModel;

import java.util.UUID;

public class TestFixtures {
    public static final String BASE_TASK_URL = "tasks";
    public static final String ID_TASK_URL = "tasks/%s";
    public static final String MEDIA_TYPE = "application/vdn.orthae.public.v1+json";

    public static String EXPIRED_TOKEN = "Bearer eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ1c2VyLXNlcnZpY2UiLCJpZCI6ImYxMWVhNDM5LTI3Y2YtNDQxOS1iNmQyLTFiYzkzMmVhMDc1YyIsImV4cCI6MTc0MTg5OTQxMiwicm9sZXMiOlsiQURNSU4iXSwidXNlcm5hbWUiOiJ0ZXN0In0.FWmf5GWJigN3DNrrpIIgOnPrz50KR7VGU0UUuFBRSR-hGgpWsZ0vrNPGufB5_JuUlEpsfd7SOhVm8J8qZz8Ixe6_4C-ouQMCpMAkV8rkT479gIvi_mpPO2CMWer1C2mPUA8UmxD4z2PZlQiY-p68cAhruXkmCCLn3jRIE6QEVqXO77_RC_Whcp5NTQXu_tLzLlIdyikOEl1m6JVddnrxsbZq0iSWrb8J_u8ytZeqxCPkwplU9Wko3jfBSueVG8MRRhRIgbpmU441GppN1UHQFLrVBXNlggo320NctI_Aan0XAKNp6JPs8nnRWMv7W3maqMNo6pqgct9Bshpar2Txzw";
    public static String ALIVE_ADMIN_TOKEN = "Bearer eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ1c2VyLXNlcnZpY2UiLCJpZCI6IjIxZWM1MDIwLTg5N2UtNGMzMi1hYmUxLTZjODM2ZWY1ZDZjZSIsImV4cCI6MTg5OTYyOTQwMSwicm9sZXMiOlsiQURNSU4iXSwidXNlcm5hbWUiOiJUZXN0QWRtaW4ifQ.Efb9ndrR-0yWgG__bOpYAOwSIWKn6p6QqifhQTYm9WmrGSJaZbqC09C6GobvVF-r04AVJ5NwFjWDtmeNDL1AXB_dl_tcqoS7su3SqTbeefz11CvX_519OrVtW65rAv4X1QeOmEY6jY2Jyd7Pf_Pk88Qu2F0pNpVePLb0Y1fp3waGKRYBNNTpNYjEyEnpxrHgJCnfe_0NUMSLTLkqQZbeLCibhaX9SvX0AuB5fg-2YdE2XHh9KFQgdJJeQdINtr0uRlBIEwd32D9fkevKp_UZTaKQWUbu0uTxk4lw1NPsoGyOyCXLbjKPk47UssE6uP_Pe8VVW3tcyvGJtS8Nz382FA";
    public static String ALIVE_USER_TOKEN = "Bearer eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ1c2VyLXNlcnZpY2UiLCJpZCI6ImQzYjZiMWE1LTNmM2QtNGQxNy04ZTRlLWM2ZTBjYTJhYjBiZCIsImV4cCI6MTg5OTYyOTQ0Mywicm9sZXMiOlsiQURNSU4iXSwidXNlcm5hbWUiOiJUZXN0VXNlciJ9.BwOGyEJnS3AjXAJGgLd-FrnxPOFK1O5eT7UhhJOlh9jk69e1PKMlPWkjFRrIQwzQgEuWMW_BxUz1XJpxSBSuWPDE8kUhpaIy8qaAS67ltXxypSeHEXnR1c5cX8KAI3r5aSkN23ZhLubc8nVCPUALudohm-tCoIi3r4mk4Htf7drW6ztPy5AVB4ksEmv6H1c4o_7VJtjGo0pNVVfCk4Ydnvju5HungZIZi8GRXUq174jhPnPta3uUcU60zplz4vcQql1sVmfw6AZ_lHcatkYchFQzADGgSpmV1uPlI5Wbfs68dNo2Wi42qkyB48A4mF0DHF69CZLaOAxMB5XrRZf7lQ";

    public static AuthenticatedUser AUTHENTICATED_ADMIN = new AuthenticatedUser(UUID.fromString("21ec5020-897e-4c32-abe1-6c836ef5d6ce"), "TestAdmin", Role.ADMIN);
    public static AuthenticatedUser AUTHENTICATED_USER = new AuthenticatedUser(UUID.fromString("d3b6b1a5-3f3d-4d17-8e4e-c6e0ca2ab0bd"), "TestUser", Role.USER);

    public static TaskModel TASK_MODEL = TaskModel.builder()
            .id(UUID.fromString("259735b6-0809-4890-bae4-eb4940104769"))
            .createdBy(new UserModel(AUTHENTICATED_ADMIN.getId()))
            .title("Test Model 1")
            .description("Test Model 1 Description")
            .status(TaskStatusModel.PENDING)
            .build();
}
