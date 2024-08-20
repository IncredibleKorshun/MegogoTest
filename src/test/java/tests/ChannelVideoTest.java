package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import dto.ChannelVideoResponse;
import dto.Program;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import service.ChannelVideoService;

import java.util.List;

public class ChannelVideoTest extends BaseTest{

    @Test(dataProvider = "searchData")
    @Description("Check that programs are sorted by start_timestamp")
    void checkThatProgramsSortedByStartTimestamp(Integer videoId) {
        List<ChannelVideoResponse> channelVideos = ChannelVideoService.getChannelVideo(videoId)
                .assertStatus(200)
                .getResponseBodyFromWrapper(new TypeReference<>() {});
        var channelVideo = channelVideos.stream().findFirst().orElseThrow(() -> new RuntimeException(String.format("Nothing was found by such videoId - ", videoId)));

        var iterator = channelVideo.getPrograms().iterator();
        Program previous = null;

        while (iterator.hasNext()){
            Program next = iterator.next();
            if(previous != null){
                Assert.assertTrue(next.getStart_timestamp() > previous.getStart_timestamp());
            }
            previous = next;
        }
    }

    @Test(dataProvider = "searchData")
    @Description("Check that there is a program in current timestamp")
    void checkThatThereIsAProgramInCurrentTimestamp(Integer videoId) {
        List<ChannelVideoResponse> channelVideos = ChannelVideoService.getChannelVideo(videoId).assertStatus(200)
                .getResponseBodyFromWrapper(new TypeReference<>() {});
        var channelVideo = channelVideos.stream().findFirst().orElseThrow(() -> new RuntimeException(String.format("Nothing was found by such videoId - ", videoId)));

        Long currentTimeStamp = System.currentTimeMillis()/1000;

        Assert.assertTrue(channelVideo.getPrograms().stream().anyMatch(p -> p.getStart_timestamp() <= currentTimeStamp && currentTimeStamp<= p.getEnd_timestamp()), "");
    }

    @Test(dataProvider = "searchData")
    @Description("Check that there are no programs more or less than 24 hours from current timestamp")
    void checkThatThereAreNoProgramsMoreOrLessThan24HoursFromCurrentTimeStamp(Integer videoId) {
        List<ChannelVideoResponse> channelVideos = ChannelVideoService.getChannelVideo(videoId).assertStatus(200)
                .getResponseBodyFromWrapper(new TypeReference<>() {});
        var channelVideo = channelVideos.stream().findFirst().orElseThrow(() -> new RuntimeException(String.format("Nothing was found by such videoId - ", videoId)));

        Long currentTimeStamp = System.currentTimeMillis()/1000;
        Long dayAgo = currentTimeStamp - (24 * 60 * 60);
        Long dayAfter = currentTimeStamp + (24 * 60 * 60);

        Assert.assertTrue(channelVideo.getPrograms().stream().anyMatch(p -> p.getStart_timestamp() <= dayAfter || p.getEnd_timestamp()>=dayAgo));
    }

    @DataProvider(name = "searchData")
    public Integer[] searchData() {
        return new Integer[] {
                1639111,
                1585681,
                1639231
        };
    }
}
