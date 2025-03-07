# ✅ 1. 전체 구조 개요
당신의 프로젝트는 전형적인 **Spring MVC 전통 구조**이며, **XML 설정 기반**으로 이루어져 있어요.

```plaintext
[내장 톰캣] → [DispatcherServlet] → [HandlerMapping] → [Controller] → [ViewResolver] → [JSP 렌더링]
```

---

# ✅ 2. 스프링 컨테이너 분석

스프링에는 **2종류의 컨테이너**가 동시에 작동해요.

| 구분 | 역할 | 설정 파일 |
|------|------|------------|
| **Root WebApplicationContext** | 전역 빈 관리 (Service, Repository 등) | `/WEB-INF/spring/root-context.xml` |
| **Servlet WebApplicationContext** | 서블릿 전용 빈 관리 (Controller 등) | `/WEB-INF/spring/appServlet/servlet-context.xml` |

### ✅ Root Context
- **web.xml**:
  ```xml
  <context-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>/WEB-INF/spring/root-context.xml</param-value>
  </context-param>
  <listener>
      <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>
  ```
  ➤ 서버 기동 시 `ContextLoaderListener`가 **RootContext** 생성.

### ✅ Servlet Context
- **web.xml**:
  ```xml
  <servlet>
      <servlet-name>appServlet</servlet-name>
      <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
      <init-param>
          <param-name>contextConfigLocation</param-name>
          <param-value>/WEB-INF/spring/appServlet/servlet-context.xml</param-value>
      </init-param>
  </servlet>
  ```
  ➤ URL 매핑 `/`으로 들어오는 모든 요청을 **DispatcherServlet**이 처리.

---

# ✅ 3. DispatcherServlet 역할과 처리 흐름

```
클라이언트 → (HTTP 요청) → DispatcherServlet
 → HandlerMapping (어떤 Controller로?)
 → Controller (비즈니스 로직 처리)
 → ViewResolver (어떤 JSP로?)
 → JSP 응답
```

- **DispatcherServlet**은 Spring MVC의 핵심이며, 서블릿으로 등록돼 있음.
- `@RequestMapping`으로 Controller와 URL을 매핑.

---

# ✅ 4. HomeController 분석

```java
@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        logger.info("방가방가 Welcome home! The client locale is {}.", locale);
        ...
        return "home"; // → "/WEB-INF/views/home.jsp"
    }
}
```

### ✅ 관련 어노테이션
| 어노테이션 | 역할 |
|------------|------|
| `@Controller` | 해당 클래스를 스프링 컨테이너에 **Controller 빈으로 등록** |
| `@RequestMapping` | URL 패턴과 HTTP Method 지정 |
| `Model` | View에 데이터를 전달 |
| `Locale` | 클라이언트의 로케일 정보 자동 주입 |

➤ **servlet-context.xml**에서 `@Controller`를 활성화하는 설정:
```xml
<context:component-scan base-package="dev.mvc.spring" />
<annotation-driven />
```

---

# ✅ 5. ViewResolver 동작
```xml
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="prefix" value="/WEB-INF/views/" />
    <property name="suffix" value=".jsp" />
</bean>
```

➤ Controller에서 `return "home"` 반환 →  
`/WEB-INF/views/home.jsp` 렌더링.

---

# ✅ 6. UTF-8 처리
```xml
<filter>
    <filter-name>encodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>
    </init-param>
    <init-param>
        <param-name>forceEncoding</param-name>
        <param-value>true</param-value>
    </init-param>
</filter>
```
모든 요청과 응답의 인코딩을 UTF-8로 강제 설정.  
**HTML, JSP 파일들도 반드시 `<meta charset="UTF-8">` 포함 필수**.

---

# ✅ 7. 서블릿과 스프링 컨테이너 관계
| 요소 | 역할 |
|------|------|
| **톰캣** | 서블릿 컨테이너, 웹 서버 역할 |
| **DispatcherServlet** | 프론트 컨트롤러 서블릿 |
| **ContextLoaderListener** | RootContext를 초기화 |
| **스프링 빈 컨테이너** | DispatcherServlet, ContextLoaderListener에 의해 생성된 IoC 컨테이너 |
| **Controller, Service, Repository** | 각각 ServletContext, RootContext에 등록됨 |

---

# ✅ 8. JUL(Log)과 연계
이 구조에서는
- **톰캣 기본 로그 (JUL)**: Tomcat 내부 로깅
- **SLF4J → Log4j2**: 애플리케이션 로그

이렇게 분리되어 관리됨.  
`HomeController`에서의 `logger.info()`는 **Log4j2 형식**대로 출력.

---

# ✅ ✅ ✅ 최종 정리
이 프로젝트는:
- 전통적인 **XML 기반 Spring MVC 구조**
- **내장 톰캣 사용**
- **RootContext + ServletContext 분리**
- **DispatcherServlet 기반 요청 처리**
- **@Controller + @RequestMapping** 활용
- **UTF-8 인코딩 필터 적용**
- **Log4j2로 애플리케이션 로깅 처리**

라는 흐름으로 구성되어 있고,  
여기서 이해한 로직과 구조가 **Spring Boot**로 넘어갈 때도 **완전 동일하게 적용**됩니다.
