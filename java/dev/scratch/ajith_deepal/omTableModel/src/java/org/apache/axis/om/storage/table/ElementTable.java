package org.apache.axis.om.storage.table;

import org.apache.axis.om.storage.OMStorageException;
import org.apache.axis.om.storage.column.BooleanColumn;
import org.apache.axis.om.storage.column.IntegerColumn;
import org.apache.axis.om.storage.column.StringColumn;
import org.apache.axis.om.util.OMConstants;

/**
 * Copyright 2001-2004 The Apache Software Foundation.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author Axis team
 * Date: Sep 27, 2004
 * Time: 5:10:23 PM
 * 
 */
public class ElementTable extends NodeTable{

    private static final int COLUMN_COUNT=10;

    //The next sibling type is required since e need to figure out the
    //type of the next sibling as well (whether it is Text or Element)
    private IntegerColumn nextsiblingTypeColumn = new IntegerColumn();
    private IntegerColumn nameSpaceColumn = new IntegerColumn();
    private IntegerColumn firstChildColumn = new IntegerColumn();
    private IntegerColumn firstChildTypeColumn = new IntegerColumn();
    private IntegerColumn firstAttributeColumn = new IntegerColumn();
    private BooleanColumn doneColumn = new BooleanColumn();
    private StringColumn localNameColumn = new StringColumn();

    /**
     *
     * @param localName
     * @param parentKey
     * @return
     */
    public int addElement(String localName,int parentKey){
        int key = pkGenerator.nextVal();

        keyColumn.appendValue(key);
        parentColumn.appendValue(parentKey);
        nextSiblingColumn.appendValue(OMConstants.DEFAULT_INT_VALUE);
        nextsiblingTypeColumn.appendValue(OMConstants.DEFAULT_INT_VALUE);
        nameSpaceColumn.appendValue(OMConstants.DEFAULT_INT_VALUE);
        firstChildColumn.appendValue(OMConstants.DEFAULT_INT_VALUE);
        firstChildTypeColumn.appendValue(OMConstants.DEFAULT_INT_VALUE);
        firstAttributeColumn.appendValue(OMConstants.DEFAULT_INT_VALUE);
        doneColumn.appendValue(false);
        localNameColumn.appendValue(localName);


        return key;
    }

    /**
     * returns the firstchild key of a given element
     * @param key
     * @return
     */
    public int getFirstChild(int key){
        return firstChildColumn.getValue(key);
    }

    /**
     * get the parent key of a given element
     * @param key
     * @return
     */
    public int getParent(int key){
        return parentColumn.getValue(key);
    }

    /**
     *
     * @param key
     * @return  the set of values in a particular row
     */
    public String[][] getElement(int key){
        if (!isKeyPresent(key)){
            throw new OMStorageException();
        }

        String[][] values = new String[2][COLUMN_COUNT];
        //add the lables and values
        values[0][0] = OMConstants.ID_KEY ;values[1][0] = Integer.toString(keyColumn.getValue(key));
        values[0][1] = OMConstants.PARENT_ID_KEY ;values[1][1] = Integer.toString(parentColumn.getValue(key));
        values[0][2] = OMConstants.NEXT_SIBLING_KEY ;values[1][2] = Integer.toString(nextSiblingColumn.getValue(key));
        values[0][3] = OMConstants.NEXT_SIBLING_TYPE_KEY ;values[1][3] = Integer.toString(nextsiblingTypeColumn.getValue(key));
        values[0][4] = OMConstants.NAME_SPACE_KEY ;values[1][4] = Integer.toString(nameSpaceColumn.getValue(key));
        values[0][5] = OMConstants.DONE_KEY ;values[1][5] = doneColumn.getValue(key)?"1":"0";
        values[0][6] = OMConstants.LOCAL_NAME_KEY ;values[1][6] = localNameColumn.getValue(key);
        values[0][7] = OMConstants.FIRST_CHILD_KEY ;values[1][7] =Integer.toString(firstChildColumn.getValue(key));
        values[0][8] = OMConstants.FIRST_CHILD_TYPE_KEY ;values[1][8] =Integer.toString(firstChildTypeColumn.getValue(key));
        values[0][9] = OMConstants.FIRST_ATTRIBUTE_KEY ;values[1][9] =Integer.toString(firstAttributeColumn.getValue(key));

        return values;
    }

    /**
     * updates the next sibling
     * @param key
     * @param nextSiblingkey
     * @param nextSiblingType
     */
    public void updateNextSibling(int key,int nextSiblingkey,int nextSiblingType){

        nextSiblingColumn.setValue(key,nextSiblingkey);
        nextsiblingTypeColumn.setValue(key,nextSiblingType);


    }

    /**
     * updates the done flag
     * @param key
     */
    public void updateDone(int key){
        doneColumn.setValue(key,true);
    }

    /**
     * Updates the namespace
     * @param elementKey
     * @param namespaceKey
     */
    public void updateNameSpace(int elementKey,int namespaceKey){
        nameSpaceColumn.setValue(elementKey,namespaceKey);
    }

    /**
     * updates the first child
     * @param key
     * @param firstChildKey
     * @param firstChildType
     */
    public void updateFirstChild(int key,int firstChildKey,int firstChildType){
        firstChildColumn.setValue(key,firstChildKey);
        firstChildTypeColumn.setValue(key,firstChildType);
    }

    /**
     *
     * @param key
     * @param firstChildKey
     */
    public void updateFirstAttribute(int key,int firstChildKey){
        firstAttributeColumn.setValue(key,firstChildKey);

    }

    /**
     * Debug method!!
     */
    public void dumpValues(){
        System.out.println("content of "+this);
        System.out.println("key" +
                " -  " + "parent"+
                " -  " + "nexr sib"+
                " -  " + "next sib type"+
                " -  " + "ns"+
                " -  " + "done"+
                " -  " + "fc"+
                " -  " + "fct"+
                " -  " + "ln" +
                " -  " + "firstAttr")
                ;


        for (int i = 0; i < this.getSize(); i++) {

            System.out.println(keyColumn.getValue(i) +
                    " -  " + parentColumn.getValue(i)+
                    " -  " + nextSiblingColumn.getValue(i)+
                    " -  " + nextsiblingTypeColumn.getValue(i)+
                    " -  " + nameSpaceColumn.getValue(i)+
                    " -  " + doneColumn.getValue(i)+
                    " -  " + firstChildColumn.getValue(i)+
                    " -  " + firstChildTypeColumn.getValue(i)+
                    " -  " + localNameColumn.getValue(i)+
                    " -  " + firstAttributeColumn.getValue(i)

            );


        }
    }
}
