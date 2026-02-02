package com.contegris.intelliinbox.tests;

import com.contegris.intelliinbox.base.BasePage;
import com.contegris.intelliinbox.pages.InteractionAcceptancePage;
import com.contegris.intelliinbox.pages.OutboundModalPage;
import com.contegris.intelliinbox.pages.SideBarMenuPage;
import org.testng.annotations.Test;

public class Call extends BasePage {

    @Test
    public void testOutboundCallFlow() throws Exception {
        OutboundModalPage outbound = new OutboundModalPage(driver);
        SideBarMenuPage menu = new SideBarMenuPage(driver, wait);

        System.out.println("🚀 Starting Outbound Call Flow...");
        outbound.makeOutboundCall("03057204466");
        outbound.muteUnmuteHoldSpeakerFlow();
        outbound.endCallFlow();
        menu.resetPresenceIfNeeded();
        menu.closeSideMenu();
        System.out.println("✅ Outbound Call Flow completed successfully!");
    }

    @Test
    public void testInboundCallFlow(){
        InteractionAcceptancePage intAcceptance = new InteractionAcceptancePage(driver, wait);
        intAcceptance.isIncomingRequestVisible();
        // intAcceptance.rejectIncomingInteraction();
        intAcceptance.acceptIncomingInteraction();
    }
}
