package ru.netology.springbootnginx.mapping;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import ru.netology.springbootnginx.model.Account;


//наш обькт не передается телом запрос а передается с query параметрах гет запроса, чтобы мы могли собрать из этих парамтров 1 обьект в нашем случае (Account)
// нам надо реализовать интерфейс HandlerMethodArgumentResolver в реализации которого мы опишем логику сбора такого обьекта
public class AccountHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {


    //Реализуем метод supportsParameter(MethodParameter methodParameter). Этот метод должен возвращать значение true, если тип параметра метода - наш класс,
    // и значение false в противном случае.
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(Account.class);
    }


    //Реализуйте метод resolveArgument(), выполнив следующие действия:
    //1.Получите значение параметра запроса 'user'.
    //2.Получите значение параметра запроса 'password'.
    //3.Если параметр запроса 'user' не найден в запросе, используйте значение по умолчанию.
    //4.Если параметр запроса 'password' не найден в запросе, используйте значение по умолчанию.
    //5.Создайте новый объект Account и верните созданный объект.
    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        String user = nativeWebRequest.getParameter("user");
        String password = nativeWebRequest.getParameter("password");

        if (isNotSet(user)) {
            user = "defaultUser";
        }

        if (isNotSet(password)) {
            password = "defaultPassword";
        }

        return new Account(user, password);
    }

    private boolean isNotSet(String value) {
        return value == null;
    }
}