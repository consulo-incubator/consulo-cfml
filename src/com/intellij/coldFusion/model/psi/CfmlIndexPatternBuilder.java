/*
 * Copyright 2000-2013 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.coldFusion.model.psi;

/**
 * User: Nadya.Zabrodina
 * Date: 9/5/11
 */

import com.dci.intellij.dbn.language.sql.SQLLanguage;
import com.intellij.coldFusion.model.files.CfmlFile;
import com.intellij.coldFusion.model.lexer.CfmlLexer;
import com.intellij.coldFusion.model.lexer.CfmlTokenTypes;
import com.intellij.coldFusion.model.parsers.CfmlElementTypes;
import com.intellij.lang.LanguageParserDefinitions;
import com.intellij.lang.LanguageVersion;
import com.intellij.lang.ParserDefinition;
import com.intellij.lexer.LayeredLexer;
import com.intellij.lexer.Lexer;
import com.intellij.lexer.XmlHighlightingLexer;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.search.IndexPatternBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.util.LanguageVersionUtil;


/**
 */
public class CfmlIndexPatternBuilder implements IndexPatternBuilder
{
	@Override
	public Lexer getIndexingLexer(final PsiFile file)
	{
		if(file instanceof CfmlFile)
		{
			LayeredLexer cfmlLayeredLexer = new LayeredLexer(new CfmlLexer(true, file.getProject()));

			cfmlLayeredLexer.registerLayer(new XmlHighlightingLexer(), CfmlElementTypes.TEMPLATE_TEXT);

			LanguageVersion<?> languageVersion = LanguageVersionUtil.findLanguageVersion(SQLLanguage.INSTANCE, file);

			ParserDefinition parserDefinition = LanguageParserDefinitions.INSTANCE.forLanguage(SQLLanguage.INSTANCE);
			cfmlLayeredLexer.registerLayer(parserDefinition.createLexer(file.getProject(), languageVersion), CfmlElementTypes.SQL);

			return cfmlLayeredLexer;
		}
		return null;
	}

	@Override
	public TokenSet getCommentTokenSet(final PsiFile file)
	{
		if(file instanceof CfmlFile)
		{
			return CfmlTokenTypes.tsCOMMENTS;
		}
		return null;
	}

	@Override
	public int getCommentStartDelta(final IElementType tokenType)
	{
		return 0;
	}

	@Override
	public int getCommentEndDelta(final IElementType tokenType)
	{
		return 0;
	}
}