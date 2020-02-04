package com.insight.Lib;

import com.insight.ObjRepo.CommonCanadaPage;

public class CommonCanadaLib extends CommonCanadaPage {
    public boolean veriffySelectedUser() throws Throwable{
        return isVisibleOnly(rbtnConvertAllTransactions,"radio button selected");
    }

}
