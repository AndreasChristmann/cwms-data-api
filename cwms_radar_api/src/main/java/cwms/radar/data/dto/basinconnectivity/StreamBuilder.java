package cwms.radar.data.dto.basinconnectivity;

import cwms.radar.data.dto.basinconnectivity.buildercontracts.*;
import cwms.radar.data.dto.basinconnectivity.buildercontracts.stream.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class StreamBuilder implements BuildStream, BuildDiversionStation, BuildConfluenceStation, BuildDiversionBank, BuildConfluenceBank
{
    private final String _officeId;
    private final String _streamId;
    private final boolean _startsDownstream;
    private String _divertingStreamId; //flows from
    private String _receivingStreamId; //flows into
    private String _confluenceBank;
    private String _diversionBank;
    private final Double _streamLength;
    private Double _confluenceStation;
    private Double _diversionStation;
    private final List<StreamLocation> _streamLocations = new ArrayList<>();
    private final List<Stream> _tributaries = new ArrayList<>();//streams that flow into this
    private final List<StreamReach> _streamReaches = new ArrayList<>();
    private String _comment;
    private Double _averageSlope;

    public StreamBuilder(String streamId, boolean startsDownstream, Double streamLength, String officeId)
    {
        _streamId = streamId;
        _startsDownstream = startsDownstream;
        _streamLength = streamLength;
        _officeId = officeId;
    }

    public StreamBuilder(Stream stream)
    {
        _streamId = stream.getStreamId();
        _startsDownstream = stream.startsDownstream();
        _streamLength = stream.getStreamLength();
        _officeId = stream.getOfficeId();
        _averageSlope = stream.getAverageSlope();
        _comment = stream.getComment();
        _receivingStreamId = stream.getReceivingStreamId();
        _confluenceStation = stream.getConfluenceStation();
        _confluenceBank = stream.getConfluenceBank();
        _divertingStreamId = stream.getDivertingStreamId();
        _diversionStation = stream.getDiversionStation();
        _diversionBank = stream.getDiversionBank();
        _tributaries.clear();
        _tributaries.addAll(stream.getTributaries());
        _streamReaches.clear();
        _streamReaches.addAll(stream.getStreamReaches());
        _streamLocations.clear();
        _streamLocations.addAll(stream.getStreamLocations());
    }

    public BuildDiversionStation withDivertingStreamId(String divertingStreamId)
    {
        _divertingStreamId = divertingStreamId;
        return this;
    }

    public BuildConfluenceStation withReceivingStreamId(String receivingStreamId)
    {
        _receivingStreamId = receivingStreamId;
        return this;
    }

    @Override
    public BuildStream withDiversionBank(String diversionBank)
    {
        _diversionBank = diversionBank;
        return this;
    }

    @Override
    public BuildStream withConfluenceBank(String confluenceBank)
    {
        _confluenceBank = confluenceBank;
        return this;
    }

    @Override
    public BuildConfluenceBank withConfluenceStation(Double confluenceStation)
    {
        _confluenceStation = confluenceStation;
        return this;
    }

    @Override
    public BuildDiversionBank withDiversionStation(Double diversionStation)
    {
        _diversionStation = diversionStation;
        return this;
    }

    @Override
    public Stream build()
    {
        return new Stream(this);
    }

    public BuildStream withStreamLocations(Collection<StreamLocation> streamLocations)
    {
        _streamLocations.clear();
        _streamLocations.addAll(streamLocations);
        return this;
    }

    public BuildStream withTributaries(Collection<Stream> tributaries)
    {
        _tributaries.clear();
        _tributaries.addAll(tributaries);
        return this;
    }

    public BuildStream withStreamReaches(Collection<StreamReach> streamReaches)
    {
        _streamReaches.clear();
        _streamReaches.addAll(streamReaches);
        return this;
    }

    public BuildStream withComment(String comment)
    {
        _comment = comment;
        return this;
    }

    public BuildStream withAverageSlope(Double averageSlope)
    {
        _averageSlope = averageSlope;
        return this;
    }

    String getStreamId()
    {
        return _streamId;
    }

    boolean startDownstream()
    {
        return _startsDownstream;
    }

    Double getStreamLength()
    {
        return _streamLength;
    }

    String getDivertingStreamId()
    {
        return _divertingStreamId;
    }

    String getReceivingStreamId()
    {
        return _receivingStreamId;
    }

    Double getDiversionStation()
    {
        return _diversionStation;
    }

    Double getConfluenceStation()
    {
        return _confluenceStation;
    }

    String getDiversionBank()
    {
        return _diversionBank;
    }

    String getConfluenceBank()
    {
        return _confluenceBank;
    }

    List<StreamLocation> getStreamLocations()
    {
        return new ArrayList<>(_streamLocations);
    }

    List<StreamReach> getStreamReaches()
    {
        return new ArrayList<>(_streamReaches);
    }

    List<Stream> getTributaries()
    {
        return new ArrayList<>(_tributaries);
    }

    String getComment()
    {
        return _comment;
    }

    Double getAverageSlope()
    {
        return _averageSlope;
    }

    String getOfficeId()
    {
        return _officeId;
    }

}

