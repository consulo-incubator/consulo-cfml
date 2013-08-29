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

import com.intellij.coldFusion.model.files.CfmlFile;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiType;
import org.jetbrains.annotations.NotNull;

/**
 * @author vnikolaenko
 * @date 16.02.11
 */
public class CfmlNewExpression extends CfmlCompositeElement implements CfmlTypedElement, CfmlExpression {
  public CfmlNewExpression(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public PsiType getPsiType() {
    CfmlComponentConstructorCall childByClass = findChildByClass(CfmlComponentConstructorCall.class);
    if (childByClass != null) {
      CfmlComponentReference referenceExpression = childByClass.getReferenceExpression();
      if (referenceExpression != null) {
        CfmlFile containingFile = getContainingFile();
        if (containingFile != null) {
          return new CfmlComponentType(referenceExpression.getComponentQualifiedName(referenceExpression.getText()),
                                       containingFile,
                                       getProject());
        }
      }
    }
    return null;
  }

  /*
  @Override
  public PsiReference getReference() {
    CfmlFunctionCallExpression childFunction = findChildByClass(CfmlFunctionCallExpression.class);
    if (childFunction != null) {
      return childFunction.getReferenceExpression();
    }
    return super.getReference();
  }
  */
}
