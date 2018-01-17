package com.oxygenxml.prolog.updater;

import java.io.IOException;

import javax.swing.text.BadLocationException;

import org.junit.Test;
import org.mockito.Mockito;
import org.xml.sax.SAXException;

import com.oxygenxml.prolog.updater.dita.editor.DitaTopicTextEditor;
import com.oxygenxml.prolog.updater.dita.editor.DocumentType;
import com.oxygenxml.prolog.updater.prolog.content.PrologContentCreator;
import com.oxygenxml.prolog.updater.utils.ElementXPathConstants;
import com.oxygenxml.prolog.updater.utils.ElementXPathUtils;

import junit.framework.TestCase;
import ro.sync.exml.editor.xmleditor.operations.context.RelativeInsertPosition;
import ro.sync.exml.workspace.api.editor.page.text.xml.TextDocumentController;
import ro.sync.exml.workspace.api.editor.page.text.xml.TextOperationException;
import ro.sync.exml.workspace.api.editor.page.text.xml.WSXMLTextEditorPage;
import ro.sync.exml.workspace.api.editor.page.text.xml.WSXMLTextNodeRange;
import ro.sync.exml.workspace.api.editor.page.text.xml.XPathException;

public class DitaTopicUpdateTextModeTest extends TestCase{

	static final String AUTHOR_NAME = "test";
  private WSXMLTextEditorPage wsTextEditorPage;
  private TextDocumentController textDocumentController;
  private PrologContentCreator prologContentCreater;
  private DitaTopicTextEditor ditaTopicTextEditor;
	
  @Override
  protected void setUp() throws Exception {
    //Mock the WSXMLTextEditorPage
    wsTextEditorPage = Mockito.mock(WSXMLTextEditorPage.class);

    Mockito.when(wsTextEditorPage.findElementsByXPath(ElementXPathConstants.ROOT_MAP_XPATH))
    .thenReturn(new WSXMLTextNodeRange[0]);
    Mockito.when(wsTextEditorPage.findElementsByXPath(ElementXPathConstants.ROOT_BOOKMAP_XPATH))
    .thenReturn(new WSXMLTextNodeRange[0]);
    
    //Mock the TextDocumentController
    textDocumentController = Mockito.mock(TextDocumentController.class);
    Mockito.when(wsTextEditorPage.getDocumentController()).thenReturn(textDocumentController);

    //Create prolog content creator
    prologContentCreater = new PrologContentCreator(AUTHOR_NAME);

    //Create ditaTopicTextEditor
    ditaTopicTextEditor = new DitaTopicTextEditor(wsTextEditorPage, prologContentCreater);
  }
	
	
	 /**
   *  <p><b>Description:</b>Check if insertXMLFragment is call when the prolog element is found, but it's empty(doesn't contain author or critdates).</p>
   * 
   * @throws IOException
   * @throws SAXException
   * @throws BadLocationException
   */
  @Test
  public void testUpdateProlog() throws TextOperationException, XPathException {
		Mockito.when(wsTextEditorPage.findElementsByXPath(ElementXPathConstants.PROLOG_XPATH)).
		thenReturn(new WSXMLTextNodeRange[1]);
		
		Mockito.when(wsTextEditorPage.findElementsByXPath(ElementXPathConstants.PROLOG_AUTHORS)).
		thenReturn(new WSXMLTextNodeRange[0]);
		
		Mockito.when(wsTextEditorPage.findElementsByXPath(ElementXPathConstants.PROLOG_CRITDATES)).
		thenReturn(new WSXMLTextNodeRange[0]);
		
		
		ditaTopicTextEditor.updateProlog(true);
		
		Mockito.verify(textDocumentController, Mockito.times(2)).insertXMLFragment(Mockito.anyString(), Mockito.anyString(), Mockito.any(RelativeInsertPosition.class));
		Mockito.reset(textDocumentController);
	}
	
	/**
   * <p><b>Description:</b> Check if insertXMLFragment is call when the prolog tag is found and it contains the creator.</p>
   *
   * @throws Exception
   */
	@Test
	public void testUpdateAuthor() throws XPathException, TextOperationException {
    Mockito.when(wsTextEditorPage.findElementsByXPath(ElementXPathConstants.PROLOG_XPATH))
        .thenReturn(new WSXMLTextNodeRange[1]);

    Mockito.when(wsTextEditorPage.findElementsByXPath(ElementXPathConstants.PROLOG_AUTHORS)).
    thenReturn(new WSXMLTextNodeRange[1]);
    
    Mockito.when(wsTextEditorPage.evaluateXPath(ElementXPathConstants.PROLOG_AUTHORS_CREATOR))
        .thenReturn(new WSXMLTextNodeRange[1]);

    Mockito.when(wsTextEditorPage.findElementsByXPath(ElementXPathConstants.PROLOG_CRITDATES))
        .thenReturn(new WSXMLTextNodeRange[0]);

    ditaTopicTextEditor.updateProlog(true);

    Mockito.verify(textDocumentController, Mockito.times(1)).insertXMLFragment(Mockito.anyString(), Mockito.anyString(),
        Mockito.any(RelativeInsertPosition.class));
    Mockito.reset(textDocumentController);
  }
	
	/**
   * <p><b>Description:</b> Check if insertXMLFragment is call when the prolog tag is found and it contains the critdates.</p>
   *
   * @throws Exception
   */
	@Test
  public void testUpdateCritdates() throws XPathException, TextOperationException {
    Mockito.when(wsTextEditorPage.findElementsByXPath(ElementXPathConstants.PROLOG_XPATH))
        .thenReturn(new WSXMLTextNodeRange[1]);

    Mockito.when(wsTextEditorPage.findElementsByXPath(ElementXPathConstants.PROLOG_AUTHORS)).
    thenReturn(new WSXMLTextNodeRange[0]);
    
    Mockito.when(wsTextEditorPage.evaluateXPath(ElementXPathConstants.PROLOG_AUTHORS_CREATOR))
        .thenReturn(new WSXMLTextNodeRange[0]);

    Mockito.when(wsTextEditorPage.findElementsByXPath(ElementXPathConstants.PROLOG_CRITDATES))
        .thenReturn(new WSXMLTextNodeRange[1]);
    
    Mockito.when(wsTextEditorPage.evaluateXPath(ElementXPathUtils.getCreatedXpath(DocumentType.TOPIC)))
    .thenReturn(new WSXMLTextNodeRange[1]);

    ditaTopicTextEditor.updateProlog(true);

    Mockito.verify(textDocumentController, Mockito.times(1)).insertXMLFragment(Mockito.anyString(), Mockito.anyString(),
        Mockito.any(RelativeInsertPosition.class));
    Mockito.reset(textDocumentController);
  }
	
	/**
   * <p><b>Description:</b> Check if insertXMLFragment is not call when the prolog contains the creator and critdates</p>
   *
   * @throws Exception
   */
  @Test
  public void testUpdateWhenContainsContent() throws XPathException, TextOperationException {
    Mockito.when(wsTextEditorPage.findElementsByXPath(ElementXPathConstants.PROLOG_XPATH))
        .thenReturn(new WSXMLTextNodeRange[1]);

    Mockito.when(wsTextEditorPage.findElementsByXPath(ElementXPathConstants.PROLOG_AUTHORS)).
    thenReturn(new WSXMLTextNodeRange[1]);
    
    Mockito.when(wsTextEditorPage.evaluateXPath(ElementXPathConstants.PROLOG_AUTHORS_CREATOR))
    .thenReturn(new WSXMLTextNodeRange[1]);

    Mockito.when(wsTextEditorPage.findElementsByXPath(ElementXPathConstants.PROLOG_CRITDATES))
    .thenReturn(new WSXMLTextNodeRange[1]);

    Mockito.when(wsTextEditorPage.evaluateXPath(ElementXPathConstants.PROLOG_CREATED_ELEMENT))
        .thenReturn(new WSXMLTextNodeRange[1]);

    ditaTopicTextEditor.updateProlog(true);

    Mockito.verify(textDocumentController, Mockito.times(0)).insertXMLFragment(Mockito.anyString(), Mockito.anyString(),
        Mockito.any(RelativeInsertPosition.class));
    Mockito.reset(textDocumentController);
  }

}
