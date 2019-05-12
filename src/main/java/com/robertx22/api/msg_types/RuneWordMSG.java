package com.robertx22.api.msg_types;

import com.robertx22.database.runewords.RuneWord;
import com.robertx22.db_lists.RuneWords;

public class RuneWordMSG implements MineAndSlashMSG {

    public RuneWord word;

    public RuneWordMSG(RuneWord word) {
        this.word = word;
    }

    @Override
    public final void register() {
        RuneWords.All.put(word.GUID(), word);
    }

}
