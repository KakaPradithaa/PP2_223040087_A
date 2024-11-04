/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pertemuan7.Membership.src.view.member;

/**
 *
 * @author LEGION
 */

import pertemuan7.Membership.src.dao.MemberDao;
import pertemuan7.Membership.src.model.JenisMember;
import pertemuan7.Membership.src.model.Member;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class MemberButtonSimpanActionListener implements ActionListener {
    private MemberFrame memberFrame;
    private MemberDao memberDao;

    public MemberButtonSimpanActionListener(MemberFrame memberFrame, MemberDao memberDao) {
        this.memberFrame = memberFrame;
        this.memberDao = memberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nama = this.memberFrame.getNama();
        
        if (nama.isEmpty()) {
            this.memberFrame.showAlert("Nama tidak boleh kosong");
        } else {
            JenisMember jenisMember = this.memberFrame.getJenisMember();
            Member member = new Member();
            member.setId(UUID.randomUUID().toString());
            member.setNama(nama);
            member.setJenisMember(jenisMember);

            this.memberFrame.addMember(member);
            this.memberDao.insert(member);
        }
    }
}