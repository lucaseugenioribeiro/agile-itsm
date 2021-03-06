package br.com.centralit.citajax.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import br.com.centralit.citajax.html.DocumentHTML;

public class CITScriptProcess {

    public void processScript(final String methodName, final Object objetoClasse, final Object bean, final DocumentHTML document,
            final HttpServletRequest request, final HttpServletResponse response) {
        final Context cx = Context.enter();
        final Scriptable scope = cx.initStandardObjects();

        scope.put("document", scope, document);
        scope.put("bean", scope, bean);
        scope.put("request", scope, request);
        scope.put("response", scope, response);
        scope.put("methodName", scope, methodName);
        scope.put("objetoClasse", scope, objetoClasse);

        String sourceName = objetoClasse.getClass().getName() + "_" + methodName;
        sourceName = sourceName.replaceAll("\\.", "_");

        URL url = objetoClasse.getClass().getResource("/dynamicScript/SYSTEM_" + sourceName + ".script");
        URI uri = null;
        try {
            if (url != null) {
                uri = url.toURI();
            }
        } catch (final URISyntaxException e1) {
            e1.printStackTrace();
            uri = null;
        }
        File f = null;
        if (uri != null) {
            f = new File(uri);
        }
        FileInputStream is = null;
        try {
            if (f != null) {
                is = new FileInputStream(f);
            }
        } catch (final FileNotFoundException e1) {
            e1.printStackTrace();
            is = null;
        }
        if (is != null) {
            String conteudoScript = "";
            try {
                conteudoScript = this.getConteudo(is);
            } catch (final IOException e) {
                e.printStackTrace();
            }
            try {
                is.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
            if (conteudoScript != null && !conteudoScript.trim().equalsIgnoreCase("")) {
                final Object result = cx.evaluateString(scope, conteudoScript, sourceName, 1, null);
                document.setReturnScriptSystem(result);
            }
        }
        url = objetoClasse.getClass().getResource("/dynamicScript/USER_" + objetoClasse.getClass().getName() + "_" + methodName + ".script");
        uri = null;
        try {
            if (url != null) {
                uri = url.toURI();
            }
        } catch (final URISyntaxException e1) {
            e1.printStackTrace();
            uri = null;
        }
        f = null;
        if (uri != null) {
            f = new File(uri);
        }
        try {
            if (f != null) {
                is = new FileInputStream(f);
            }
        } catch (final FileNotFoundException e1) {
            e1.printStackTrace();
        }
        if (is != null) {
            String conteudoScript = "";
            try {
                conteudoScript = this.getConteudo(is);
            } catch (final IOException e) {
                e.printStackTrace();
            }
            try {
                is.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
            if (conteudoScript != null && !conteudoScript.trim().equalsIgnoreCase("")) {
                final Object result = cx.evaluateString(scope, conteudoScript, sourceName, 1, null);
                document.setReturnScriptUser(result);
            }
        }
    }

    public String getConteudo(final FileInputStream is) throws IOException {
        String conteudo = "";
        if (is != null) {
            int ch;
            while ((ch = is.read()) != -1) {
                conteudo += (char) ch;
            }
        }
        return conteudo;
    }

}
